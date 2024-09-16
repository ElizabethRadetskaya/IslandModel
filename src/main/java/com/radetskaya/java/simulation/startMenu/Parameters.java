package com.radetskaya.java.simulation.startMenu;



import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.simulation.IslandSimulation;

import java.util.Scanner;

/**
 * Клас Parameters призначений для зміни параметрів симуляції острова на початку її виконання.
 * Він дозволяє користувачу налаштувати розміри острова, кількість хижаків, травоїдних і рослин перед початком моделювання.
 * Якщо користувач не змінює параметри, використовуються значення за замовчуванням, визначені в симуляції.
 */
public class Parameters {
    /**
     * Це основний метод, який викликається для ініціалізації параметрів симуляції.
     * Він викликає інші методи для зміни розміру острова, кількості хижаків, травоїдних і рослин.
     */
    public void changeParameters() {
        changeIslandSize();
        int countPredators = changePredatorsSize();
        int countHerbivores = changeHerbivoresSize();
        int countPlants = changePlantsSize();

        if (countHerbivores == 0) {
            countHerbivores = IslandSimulation.getInstance().getCountHerbivores();
        }
        if (countPredators == 0) {
            countPredators = IslandSimulation.getInstance().getCountPredators();
        }
        if (countPlants == 0) {
            countPlants = IslandSimulation.getInstance().getCountPlants();
        }
        //Після налаштувань симуляція створюється методом
        IslandSimulation.getInstance().createIslandModel(countHerbivores, countPredators, countPlants);
    }

    /**
     * Метод зміни розміру острова користувачем
     */
    private void changeIslandSize() {
        System.out.println("Чи хочете ви змінити розмір острова (10x4)?");
        System.out.println("1. Так");
        System.out.println("2. Ні");
        System.out.print("Введіть номер режиму: ");
        int answer = takeInt(1, 2);
        if (answer == 1) {
            System.out.println("Введіть бажаний розмір острова");
            System.out.println("Кількість рядків:");
            int rows = takeInt(1, 500);
            System.out.println("Кількість стовпців:");
            int columns = takeInt(1, 500);
            IslandField.getInstance().initializeLocations(rows, columns);
        } else {
            IslandField.getInstance().initializeLocations();
        }
    }

    /**
     * Метод зміни кількості хижаків
     * @return countPredators Кількість хижаків
     */
    private int changePredatorsSize() {
        System.out.println("Чи хочете ви змінити кількість хижаків (25)?");
        System.out.println("1. Так");
        System.out.println("2. Ні");
        System.out.print("Введіть номер режиму: ");
        int countPredators = 0;
        int answer = takeInt(1, 2);
        if (answer == 1) {
            System.out.println("Введіть бажану кількість хижаків від 5 до 500!");
            System.out.println("Кількість хижаків:");
            countPredators = takeInt(5, 500);
        }
        return countPredators;
    }

    /**
     * Метод для изменения количества травоядных
     * @return countHerbivores Кількість травоїдних
     */
    private int changeHerbivoresSize() {
        System.out.println("Чи хочете ви змінити кількість травоїдних (30)?");
        System.out.println("1. Так");
        System.out.println("2. Ні");
        System.out.print("Введіть номер режиму ");
        int countHerbivores = 0;
        int answer = takeInt(1, 2);

        if (answer == 1) {
            System.out.println("Введіть бажану кількість травоїдних від 10 до 500!");
            System.out.println("Кількість травоїдних");
            countHerbivores = takeInt(10, 500);
        }
        return countHerbivores;
    }
    /**
     * Метод для зміни кількості рослин
     * @return countPlants Кількість рослин
     */
    private int changePlantsSize() {
        System.out.println("Чи хочете ви змінити кількість рослин (10)?");
        System.out.println("1. Так");
        System.out.println("2. Ні");
        System.out.print("Введите номер режима: ");
        int countPlants = 0;
        int answer = takeInt(1, 2);

        if (answer == 1) {
            System.out.println("Введіть потрібну кількість рослин від 1 до 500!");
            System.out.println("Кількість рослин:");
            countPlants = takeInt(1, 500);
        }
        return countPlants;
    }

    /**
     * Метод для зчитування цілого числа з клавіатури у заданому діапазоні
     * @param lowNum Нижня межа діапазону
     * @param highNum Верхня межа діапазону
     * @return number Прочитане число
     */
    public int takeInt(int lowNum, int highNum) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                if (number >= lowNum && number <= highNum) {
                    return number;
                } else {
                    System.out.println("Помилка! Введене число не знаходиться у заданому діапазоні. Спробуйте ще раз:");
                }
            } else {
                scanner.next();
                System.out.println("Помилка! Введено некоректне значення. Спробуйте ще раз::");
            }
        }
    }
}