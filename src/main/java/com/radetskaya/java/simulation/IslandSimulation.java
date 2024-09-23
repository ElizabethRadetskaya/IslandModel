package com.radetskaya.java.simulation;

import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.animal.herbivore.*;
import com.radetskaya.java.animal.predator.*;
import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.field.Location;
import com.radetskaya.java.plant.Plant;
import com.radetskaya.java.simulation.thread.PlantGrowthTask;
import com.radetskaya.java.simulation.thread.StatisticsTask;
import com.radetskaya.java.simulation.thread.animalLifecycleTask.AnimalLifecycleTask;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Клас IslandSimulation моделює екосистему острова,
 * яка включає в себе травоїдних тварин, хижаків і рослини.
 * Він реалізує підхід Singleton, щоб бути впевненим, що існує лише один екземпляр цієї симуляції.
 * Основна функціональність цього класу полягає у створенні екосистеми, розміщенні різних форм життя на острові,
 * а також управлінні життєвим циклом тварин і рослин.
 */
public class IslandSimulation {
    private final long startTime;//час початку симуляції, який використовується для відстеження поточного часу симуляці
    private final int countHerbivores = 35;//кількість травоїдни
    private final int countPlants = 40;//кількість рослин
    private final int countPredators = 20;//кількість хижаків
    private static volatile IslandSimulation instance;//статичне поле, яке зберігає єдиний екземпляр класу (реалізація патерну Singleton)
    private volatile ScheduledExecutorService executorService;//об’єкт ScheduledExecutorService, який управляє виконанням різних завдань симуляції в багатопотоковому середовищі
    private int deathsCount;  // Лічильник смертей

    private IslandSimulation() {//Конструктор Приватний: робить клас Singleton, тому створити новий екземпляр можна тільки через метод getInstance(). Встановлює час початку симуляції
        startTime = System.currentTimeMillis();
    }

    /**
     * Получить экземпляр класса IslandSimulation (Singleton)
     *
     * @return instance Экземпляр класса IslandSimulation
     */
    public static IslandSimulation getInstance() {//метод Забезпечує доступ до єдиного екземпляра класу.
        // Використовується подвійна перевірка з синхронізацією, щоб забезпечити створення лише одного екземпляра навіть у багатопотоковому середовищі.
        if (instance == null) {
            synchronized (IslandSimulation.class) {
                if (instance == null) {
                    instance = new IslandSimulation();
                }
            }
        }
        return instance;
    }

    /**
     * Створює модель острова з вказаною кількістю травоїдних, хижаків і рослин.
     * Для цього викликаються методи placeHerbivores(), placePredators(), і placePlants(), які додають відповідні організми на острів.
     *
     * @param countHerbivores Кількість травоїдних
     * @param countPredators  Кількість хижвків
     * @param countPlants     Кількість рослин
     */
    public void createIslandModel(int countHerbivores, int countPredators, int countPlants) {
        placeHerbivores(countHerbivores);
        placePredators(countPredators);
        placePlants(countPlants);

        runIslandModel();
    }

    /**
     * Створює модель з кількістю тварин і рослин за замовчуванням, використовуючи значення з полів класу
     */
    public void createIslandModel() {
        placeHerbivores(countHerbivores);
        placePredators(countPredators);
        placePlants(countPlants);

        runIslandModel();
    }

    /**
     * Запускає симуляцію острова, створюючи пул потоків для виконання основних завдань:
     * життєвий цикл тварин (AnimalLifecycleTask), ріст рослин (PlantGrowthTask), і виведення статистики (StatisticsTask)
     */
    private void runIslandModel() {
        executorService = Executors.newScheduledThreadPool(3);

        AnimalLifecycleTask animalLifecycleTask = new AnimalLifecycleTask();
        PlantGrowthTask plantGrowthTask = new PlantGrowthTask();
        StatisticsTask statisticsTask = new StatisticsTask(animalLifecycleTask.getAnimalEatTask(), animalLifecycleTask.getAnimalHpDecreaseTask(), animalLifecycleTask.getObjectMultiplyTask());

        //У методі runIslandModel() інтервали часу, які задаються для задач за допомогою executorService.scheduleAtFixedRate(), визначають, як часто ці задачі будуть виконуватись
        //command — задача, яку потрібно виконати (об’єкт, що реалізує інтерфейс Runnable)
        //initialDelay — початкова затримка перед першим виконанням задачі
        //unit — одиниці вимірювання часу (секунди)
        executorService.scheduleAtFixedRate(animalLifecycleTask, 1, 8, TimeUnit.SECONDS);
        //Задача: animalLifecycleTask (життєвий цикл тварин).
        //Початкова затримка: 1 секунда перед першим запуском.
        //Інтервал: кожні 8 секунд задача буде повторюватися.
        //Призначення: Ця задача включає харчування, розмноження, зменшення здоров’я та переміщення тварин.
        // Вона виконується регулярно, через кожні 8 секунд, після першої затримки у 1 секунду.
        executorService.scheduleAtFixedRate(plantGrowthTask, 40, 30, TimeUnit.SECONDS);
        //Задача: plantGrowthTask (ріст рослин).
        //Початкова затримка: 40 секунд.
        //Інтервал: кожні 30 секунд після першого виконання.
        //Призначення: Рослини почнуть рости через 40 секунд після старту симуляції, а потім ріст буде повторюватися кожні 30 секунд.
        executorService.scheduleAtFixedRate(statisticsTask, 0, 8, TimeUnit.SECONDS);
        //Задача: statisticsTask (виведення статистики).
        //Початкова затримка: 0 секунд, тобто задача виконається одразу після запуску.
        //нтервал: кожні 8 секунд після першого запуску.
        //Призначення: Виведення статистики симуляції (кількість живих тварин, з’їдених, народжених тощо)
        // відбувається кожні 8 секунд.
    }

    /**
     * Методи для створення організмів:
     * createHerbivores(int countHerbivores), createPredators(int countPredators), createPlants(int countPlants):
     * створюють списки травоїдних, хижаків і рослин відповідно до заданої кількості.
     * Для цього використовується випадковий вибір видів тварин та рефлексія для створення нових екземплярів.
     *
     * @param countHerbivores Кілткість травоядних
     * @return herbivores Список травоядних
     */
    private List<Herbivore> createHerbivores(int countHerbivores) {
        List<Herbivore> herbivores = new ArrayList<>();
        Random random = new Random();

        // Створюємо по одній тварині кожного виду
        herbivores.add(new Buffalo());
        herbivores.add(new Caterpillar());
        herbivores.add(new Deer());
        herbivores.add(new Duck());
        herbivores.add(new Goat());
        herbivores.add(new Horse());
        herbivores.add(new Mouse());
        herbivores.add(new Rabbit());
        herbivores.add(new Sheep());
        herbivores.add(new WildBoar());

        // Генеруємо випадкову кількість тварин кожного виду, щонайменше 1
        int remainingCount = countHerbivores - herbivores.size();
        for (int i = 0; i < remainingCount; i++) {
            // Генеруємо випадковий індекс для вибору виду тварини
            int randomIndex = random.nextInt(herbivores.size());
            Herbivore randomHerbivore = herbivores.get(randomIndex);
            try {
                // Створюємо екземпляр тварини через рефлексію
                Herbivore newHerbivore = randomHerbivore.getClass().newInstance();
                herbivores.add(newHerbivore);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return herbivores;
    }

    /**
     * Створити список хижаків із заданою кількістю
     *
     * @param countPredators Кількість хижаків
     * @return predators Список хижаків
     */
    private List<Predator> createPredators(int countPredators) {
        List<Predator> predators = new ArrayList<>();
        Random random = new Random();

        // Створюємо по одній тварині кожного виду
        predators.add(new Bear());
        predators.add(new Eagle());
        predators.add(new Fox());
        predators.add(new Snake());
        predators.add(new Wolf());

        // Генеруємо випадкову кількість тварин кожного виду, щонайменше 1
        int remainingCount = countPredators - predators.size();
        for (int i = 0; i < remainingCount; i++) {
            // Генеруємо випадковий індекс для вибору виду тварини
            int randomIndex = random.nextInt(predators.size());
            Predator randomPredator = predators.get(randomIndex);
            try {
                // Створюємо екземпляр тварини через рефлексію
                Predator newPredator = randomPredator.getClass().newInstance();
                predators.add(newPredator);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return predators;
    }

    /**
     * Створити список рослин із заданою кількістю
     *
     * @param countPlants Кількість рослин
     * @return plants Список рослин
     */
    private List<Plant> createPlants(int countPlants) {
        List<Plant> plants = new ArrayList<>();
        for (int i = 0; i < countPlants; i++) {
            plants.add(new Plant());
        }
        return plants;
    }

    /**
     * Розмістити травоїдних на острові
     *
     * @param countHerbivores Кількість травоїдних
     */
    public void placeHerbivores(int countHerbivores) {
        List<Herbivore> herbivores = createHerbivores(countHerbivores);
        Random random = ThreadLocalRandom.current();
        for (Herbivore herbivore : herbivores) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(IslandField.getInstance().getNumRows());
                int column = random.nextInt(IslandField.getInstance().getNumColumns());
                Location location = IslandField.getInstance().getLocation(row, column);
                if (location.getAnimals().stream().filter(c -> c.getName().equals(herbivore.getName())).toList().size() <= herbivore.getMaxPopulation()) {
                    IslandField.getInstance().addAnimal(herbivore, row, column);
                    placed = true;
                }
            }
        }
    }

    /**
     * Розмістити хижаків на острові
     *
     * @param countPredators Кількість хижаків
     */
    public void placePredators(int countPredators) {
        List<Predator> predators = createPredators(countPredators);

        Random random = ThreadLocalRandom.current();
        for (Predator predator : predators) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(IslandField.getInstance().getNumRows());
                int column = random.nextInt(IslandField.getInstance().getNumColumns());
                Location location = IslandField.getInstance().getLocation(row, column);
                if (location.getAnimals().stream().filter(c -> c.getName().equals(predator.getName())).toList().size() <= predator.getMaxPopulation()) {
                    IslandField.getInstance().addAnimal(predator, row, column);
                    placed = true;
                }
            }
        }
    }

    /**
     * Розмістити рослини на острові
     *
     * @param countPlants Кількість рослин
     */
    public void placePlants(int countPlants) {
        List<Plant> plants = createPlants(countPlants);

        Random random = ThreadLocalRandom.current();
        for (Plant plant : plants) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(IslandField.getInstance().getNumRows());
                int column = random.nextInt(IslandField.getInstance().getNumColumns());
                Location location = IslandField.getInstance().getLocation(row, column);
                if (location.getPlants().size() <= plant.getMaxPopulation()) {
                    IslandField.getInstance().addPlant(plant, row, column);
                    placed = true;
                }
            }
        }
    }

    /**
     * Повертає поточний час симуляції, обчислюючи різницю між поточним часом і часом початку симуляції.
     *
     * @return timeNow поточний час симуляції
     */
    public long getTimeNow() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    public ScheduledExecutorService getExecutorService() {
        return executorService;
    }

    public int getCountHerbivores() {
        return countHerbivores;
    }

    public int getCountPlants() {
        return countPlants;
    }

    public int getCountPredators() {
        return countPredators;
    }

    // Метод, який збільшує кількість смертей
    public void incrementDeathsCount() {
        deathsCount++;
    }

    // Метод для отримання кількості смертей
    public int getDeathsCount() {
        return deathsCount;
    }

    public void simulateStep() {
        List<Animal> allAnimals = IslandField.getInstance().getAllAnimals();

        for (Animal animal : allAnimals) {
            if (animal.getHp() <= 0) {  // Якщо тварина померла
                incrementDeathsCount();

                // Отримуємо координати тварини
                int row = animal.getRow();  // Метод для отримання рядка
                int column = animal.getColumn();  // Метод для отримання стовпця

                // Викликаємо метод для видалення тварини з вказаними координатами
                IslandField.getInstance().removeAnimal(animal, row, column);
            }
        }
    }
}