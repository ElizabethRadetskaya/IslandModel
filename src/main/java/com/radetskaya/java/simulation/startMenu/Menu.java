package com.radetskaya.java.simulation.startMenu;


import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.simulation.IslandSimulation;

/**
 * Клас для роботи з меню початку симуляції
 */
public class Menu {
    private final Parameters parameters;

    /**
     * Конструктор класу
     */
    public Menu() {
        parameters = new Parameters();
    }

    /**
     * Метод для запуску симуляції
     */
    public void startSimulation() {
        System.out.println("----------------------------------");
        System.out.println("Чи хочете ви внести свої параметри перед початком симуляції?");
        System.out.println("1. Так");
        System.out.println("2. Ні");
        System.out.print("Введіть номер режиму: ");
        int answer = parameters.takeInt(1, 2);

        if (answer == 1) {
            parameters.changeParameters();
        } else {
            IslandField.getInstance().initializeLocations();
            IslandSimulation.getInstance().createIslandModel();
        }
        System.out.println("----------------------------------");
        System.out.println("Завантаження симуляції острова...");
        System.out.println("----------------------------------");
        System.out.println();
    }
}