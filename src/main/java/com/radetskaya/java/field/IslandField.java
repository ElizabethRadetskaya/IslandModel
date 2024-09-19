package com.radetskaya.java.field;


import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.plant.Plant;

import java.util.ArrayList;
import java.util.List;

//Клас IslandField відповідає за управління полем острова, яке складається з локацій (клітинок).
//Клас організований як Singleton, що гарантує наявність лише одного екземпляра для всієї симуляції.
//Він управляє додаванням та видаленням тварин і рослин на острові, а також дозволяє доступ до всіх локацій і організмів на острові.

public class IslandField {
        private Location[][] locations; // Двумерный массив состоящий из локаций(ячеек)
        private final int numRows = 10; //default
        private final int numColumns = 4; //default
        private static volatile IslandField instance;
    /**
     * Приватний: клас реалізує Singleton, тому його екземпляр створюється лише через метод getInstance().
     *
     */
    private IslandField() {
    }

    /**
     * Метод для отримання екземпляра класу IslandField.
     *
     * @return Екземпляр класу IslandField
     */
    public static IslandField getInstance() {
        if (instance == null) {
            synchronized (IslandField.class) {
                if (instance == null) {
                    instance = new IslandField();
                }
            }
        }
        return instance;
    }

    /**
     * Метод для ініціалізації локацій (осередків) на острові.
     * Встановлює розміри двовимірного масиву та створює локації.
     *
     * @param numRows    Кількість рядків
     * @param numColumns Кількість стовпців
     */
    public void initializeLocations(int numRows, int numColumns) {
        locations = new Location[numRows][numColumns];
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                locations[i][j] = new Location(i, j);
            }
        }
    }

    /**
     * Перевантажений метод ініціалізації локацій (ячеек) на острові.
     * Використовує стандартні значення для розмірів.
     */
    public void initializeLocations() {
        locations = new Location[numRows][numColumns];
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                locations[i][j] = new Location(i, j);
            }
        }
    }

    /**
     * Метод для отримання локації (комірки) за заданими координатами.
     *
     * @param row    Номер рядка
     * @param column Номер стовпчика
     * @return Локація із заданими координатами
     */
    public synchronized Location getLocation(int row, int column) {
        return locations[row][column];
    }

    /**
     * Метод для додавання тварини до зазначеної локації.
     *
     * @param animal Тварина для додавання
     * @param row    Номер рядка локації
     * @param column Номер стовпця локації
     */
    public void addAnimal(Animal animal, int row, int column) {
        Location location = getLocation(row, column);
        location.addAnimal(animal);
    }

    /**
     * Метод видалення тварини із зазначеної локації.
     *
     * @param animal Тварина для видалення
     * @param row    Номер рядка локації
     * @param column Номер стовпця локації
     */
    public void removeAnimal(Animal animal, int row, int column) {
        Location location = getLocation(row, column);
        location.removeAnimal(animal);
    }

    /**
     * Метод додавання рослини в зазначену локацію
     *
     * @param plant  Рослина для додавання
     * @param row    Номер рядка локації
     * @param column Номер стовпця локації
     */
    public void addPlant(Plant plant, int row, int column) {
        Location location = getLocation(row, column);
        location.addPlant(plant);
    }

    /**
     * Метод видалення рослини із зазначеної локації.
     *
     * @param plant  Рослина для видалення
     * @param row    Номер рядка локації
     * @param column Номер стовпця локації
     */
    public void removePlant(Plant plant, int row, int column) {
        Location location = getLocation(row, column);
        location.removePlant(plant);
    }

    /**
     * Метод для отримання списку всіх тварин на острові.
     *
     * @return Список усіх тварин
     */
    public synchronized List<Animal> getAllAnimals() {
        List<Animal> allAnimals = new ArrayList<>();
        for (Location[] row : locations) {
            for (Location location : row) {
                allAnimals.addAll(location.getAnimals());
            }
        }
        return allAnimals;
    }

    /**
     * Метод отримання списку всіх рослин на острові.
     *
     * @return Список усіх рослин
     */
    public List<Plant> getAllPlants() {
        List<Plant> allPlants = new ArrayList<>();
        for (Location[] row : locations) {
            for (Location location : row) {
                allPlants.addAll(location.getPlants());
            }
        }
        return allPlants;
    }
    public int getNumRows() {
        return numRows;
    }
    public int getNumColumns() {
        return numColumns;
    }
}
