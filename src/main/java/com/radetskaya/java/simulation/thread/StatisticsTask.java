package com.radetskaya.java.simulation.thread;


import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.simulation.IslandSimulation;
import com.radetskaya.java.simulation.thread.animalLifecycleTask.task.AnimalEatTask;
import com.radetskaya.java.simulation.thread.animalLifecycleTask.task.AnimalHpDecreaseTask;
import com.radetskaya.java.simulation.thread.animalLifecycleTask.task.AnimalMultiplyTask;

/**
 * Відповідає за виведення статистики симуляції острова, яка охоплює такі аспекти,
 * як кількість народжених тварин, з’їдених тварин, тих, що померли від голоду,
 * а також кількість рослин на острові.
 * Крім того, він перевіряє, чи закінчився час симуляції, і якщо це так, завершить роботу симуляції.
 */
public class StatisticsTask implements Runnable {
    private boolean isTimeOver;//флаг, який показує, чи закінчився час симуляції
    private int babies;//кількість новонароджених тварин
    private int animalsEaten;//кількість з’їдених тварин
    private int animalsDiedByHungry;//кількість тварин, що померли від голоду.
    private int countAnimalsEnd;//кількість тварин, які залишилися живими на острові
    private int countPlants;//кількість рослин на острові
    private static int currentDay = 0;//змінна, що відслідковує поточний день симуляції
    private final AnimalMultiplyTask animalMultiplyTask;
    private final AnimalEatTask animalEatTask;
    private final AnimalHpDecreaseTask animalHpDecreaseTask;

    public StatisticsTask(AnimalEatTask animalEatTask, AnimalHpDecreaseTask animalHpDecreaseTask, AnimalMultiplyTask animalMultiplyTask) {
        //Конструктор Ініціалізує поля задач для розмноження, харчування і зменшення здоров’я, щоб отримувати статистику з них
        this.animalEatTask = animalEatTask;
        this.animalHpDecreaseTask = animalHpDecreaseTask;
        this.animalMultiplyTask = animalMultiplyTask;
    }

    @Override
    public void run() {
        long timeNow = IslandSimulation.getInstance().getTimeNow();
        //отримується час, що пройшов з початку симуляції. Цей час використовується для перевірки, чи закінчилася симуляція
        isTimeOver = checkTime(timeNow);

        babies = animalMultiplyTask.getBabies();//кількість народжених твари
        countAnimalsEnd = IslandField.getInstance().getAllAnimals().size(); // кількість живих тварин на острові.
        animalsEaten = animalEatTask.getAnimalsEaten(); // кількість з’їдених тварин
        animalsDiedByHungry = animalHpDecreaseTask.getAnimalsDiedByHungry(); // кількість тварин, що померли від голоду
        countPlants = IslandField.getInstance().getAllPlants().size();//кількість рослин на острові
        printStats();//Статистика виводиться через метод printStats(), який друкує інформацію про кількість тварин, рослин, смертей від голоду, народжень
        //Якщо симуляція завершена (час вичерпано), виводиться повідомлення про перемогу
        if (isTimeOver) {//Якщо час симуляції закінчився, викликається shutdown() для завершення роботи пулу потоків і симуляції
            IslandSimulation.getInstance().getExecutorService().shutdown();
            System.exit(0);
        }
    }

    /**
     *  Викликається метод checkTime(), який перевіряє, чи пройшло більше 1 хвилини симуляції (за умови, що час ділиться на 60 і порівнюється з 1)
     *
     * @param timeNow поточний час
     * @return isTimeOver true, если время истекло, иначе - false
     */
    //Перевіряє, чи пройшло більше 1 хвилини симуляції. Якщо так, повертається true, що означає, що симуляція завершилас
    private boolean checkTime(long timeNow) {
        return timeNow / 60 >= 1;
    }

    /**
     * Отримання статистики з інших задач
     */
    private void printStats() {
        if (isTimeOver) {
            System.out.println("ПЕРЕМОГА! ВИ ПРОТРИМАЛИСЯ 1 ХВИЛИНУ!");
            System.out.println("----------------------------------");
        } else {
            System.out.printf("--- ДЕНЬ %d ---", currentDay);//Оновлення дня. Щоразу, коли метод викликається, збільшується змінна currentDay, щоб відобразити поточний день симуляції
            currentDay++;
            System.out.println();
        }
        System.out.println();
        System.out.println("СТАТИСТИКА ПО ОСТРОВУ");
        System.out.println();

        System.out.print("Тварин на острові: ");
        System.out.println(countAnimalsEnd);

        System.out.print("Тварин померло з голоду: ");
        System.out.println(animalsDiedByHungry);

        System.out.print("Тварин з'їдено: ");
        System.out.println(animalsEaten);

        System.out.print("Дитинчат народилося: ");
        System.out.println(babies);

        System.out.print("Рослин на острові: ");
        System.out.println(countPlants);

        System.out.println();
        System.out.println("----------------------------------");
        System.out.println();
    }

    //Статичний метод, що повертає значення поточного дня симуляції
    public static int getCurrentDay() {
        return currentDay;
    }
}