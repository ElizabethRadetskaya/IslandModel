package com.radetskaya.java.field;

import com.radetskaya.java.lifeform.LifeForm;
import com.radetskaya.java.animal.Animal;

import com.radetskaya.java.plant.Plant;

import java.util.*;

public class Location {
    private final int row;
    private final int column;
    private final List<Animal> animals;
    private final List<Plant> plants;

    /**
     * Конструктор класу Location.
     *   Встановлює значення рядка та стовпця для цього розташування.
     *
     *   @param row Номер рядка
     *   @param column Номер стовпця
     */
    public Location(int row, int column) {
        this.row = row;
        this.column = column;

        animals = new ArrayList<>();
        plants = new ArrayList<>();
    }

    /**
     * Метод для додавання тварини в дане місце розташування.
     * Встановлює координати тварини та додає її до списку тварин.
     *
     * @param animal Тварина для додаванняя
     */
    public void addAnimal(Animal animal) {
        animal.setRow(row);
        animal.setColumn(column);

        animals.add(animal);
    }

    /**
     * Метод для видалення тварини з цього місця розташування.
     *
     * @param animal Тварина для видалення
     */
    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    /**
     * Метод для додавання рослини в дане місце розташування.
     * Встановлює координати рослини та додає її до списку рослин.
     *
     * @param plant Рослина для додавання
     */
    public void addPlant(Plant plant) {
        plant.setRow(row);
        plant.setColumn(column);
        plants.add(plant);
    }

    /**
     * Метод для видалення рослини з цього місця розташування.
     *
     * @param plant Рослина для видалення
     */
    public void removePlant(Plant plant) {
        plants.remove(plant);
    }

    /**
     * Метод для отримання списку рослин у цьому місці.
     *
     * @return Список рослин
     */
    public List<Plant> getPlants() {
        return plants;
    }

    /**
     * Метод для отримання списку тварин у цьому місці.
     *
     * @return Список тварин
     */
    public List<Animal> getAnimals() {
        return animals;
    }

    /**
     * Метод для отримання списку всіх живих форм у цьому місці.
     * Включає як тварин, так і рослини.
     *
     * @return Список живих форм
     */
    public List<LifeForm> getLifeForms() {
        List<LifeForm> lifeForms = new ArrayList<>();
        lifeForms.addAll(animals);
        lifeForms.addAll(plants);
        return lifeForms;
    }
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
}