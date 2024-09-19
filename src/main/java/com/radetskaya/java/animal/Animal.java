package com.radetskaya.java.animal;


import com.radetskaya.java.error.ObjectNotLifeFormException;
import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.field.Location;
import com.radetskaya.java.lifeform.LifeForm;
import com.radetskaya.java.plant.Plant;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends LifeForm {
    private final int step; // Швидкість переміщення, не більше ніж клітин за хід
    private final double maxHp; // Максимальна кількість кілограмів їжі потрібна тварині для повного насичення
    private double hp; // Кількість здоров'я тварини

    public Animal(double weight, int step, double maxHp, int maxPopulation, String name) {
        super(weight, maxPopulation, name);
        this.step = step;
        this.maxHp = maxHp;
        this.hp = maxHp; // На старті максимальна кількість здоров'я
    }

    /**
     * Перевіряє, чи тварина може з'їсти вказану їжу
     *
     * @param food Їжа, яку намагається з'їсти тварина
     * @return true, якщо тварина успішно з'їла їжу, інакше - false
     */
    public boolean eat(Object food) {
        double chanceToEat;
        LifeForm lifeForm = null;
        boolean animalEatFood;

        if (food instanceof LifeForm) {
            lifeForm = (LifeForm) food;
        } else {
            try {
                throw new ObjectNotLifeFormException("Об'єкт не є твариною/рослиною.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        String foodName = lifeForm.getName();
        chanceToEat = getChanceToEat(foodName);

        animalEatFood = ThreadLocalRandom.current().nextDouble() < chanceToEat;
        if (animalEatFood) {
            setHp(Math.min((getHp() + lifeForm.getWeight()), getMaxHp())); // Показник здоров'я підвищується після поїдання
            Location location = IslandField.getInstance().getLocation(lifeForm.getRow(), lifeForm.getColumn()); //Тварина/рослина видаляється зі списку мешканців локації після поїдання
            if (lifeForm instanceof Animal animal) {
                if (location.getAnimals().contains(animal)) {
                    IslandField.getInstance().removeAnimal(animal, location.getRow(), location.getColumn());
                } else {
                    return false;
                }
            } else {
                Plant plant = (Plant) lifeForm;
                if (location.getPlants().size() > 0) {
                    IslandField.getInstance().removePlant(plant, location.getRow(), location.getColumn());
                } else {
                    return false;
                }
            }
        }
        return animalEatFood;
    }

    /**
     * Абстрактний метод для отримання шансу з'їсти вказану їжу.
     *
     * @param foodName Ім'я їжі
     * @return Шанс з'їсти їжу
     */
    public abstract double getChanceToEat(String foodName);

    /**
     * Абстрактный метод для розмноження тварини з партнером.
     *
     * @param partner Партнер для размноження
     */
    public abstract void multiply(Animal partner);

    /**
     * Переміщує тварину на випадкову кількість клітин у випадковому напрямку
     */
    public void move() {
        Random random = new Random();//Створюється об’єкт Random для генерації випадкових значень.
        int randomCells = random.nextInt(getStep()) + 1;
        // генерує випадкову кількість клітинок, на яку тварина буде переміщатися, де getStep() повертає максимальну кількість кроків, яку тварина може зробити.
        int direction = random.nextInt(4);
        // генерує випадкове число від 0 до 3, що відповідає одному з чотирьох напрямків: вверх, вниз, вліво або вправо
        int newRow = getRow();
        int newColumn = getColumn();
        switch (direction) {//Залежно від напрямку змінюються координати тварина рухається...
            case 0 -> // Вверх
                    newRow -= randomCells;
            case 1 -> // Вниз
                    newRow += randomCells;
            case 2 -> // Влево
                    newColumn -= randomCells;
            case 3 -> // Вправо
                    newColumn += randomCells;
        }
        // Перевірка виходу за межі поля
        //Якщо нові координати виходять за межі поля (тобто координати менші за 0 або більші за кількість рядків/стовпців),
        // генерується новий випадковий напрямок і кількість клітинок. Це відбувається у циклі while, який повторюється, поки координати не будуть в межах допустимого поля.
        while (newRow < 0 || newRow >= IslandField.getInstance().getNumRows() || newColumn < 0 || newColumn >= IslandField.getInstance().getNumColumns()) {
            randomCells = random.nextInt(getStep()) + 1;
            direction = random.nextInt(4);

            newRow = getRow();
            newColumn = getColumn();
            switch (direction) {
                case 0 -> // Вверх
                        newRow -= randomCells;
                case 1 -> // Вниз
                        newRow += randomCells;
                case 2 -> // Влево
                        newColumn -= randomCells;
                case 3 -> // Вправо
                        newColumn += randomCells;
            }
        }
        IslandField.getInstance().removeAnimal(this, getRow(), getColumn());
        //Після того, як нові координати визначені і перевірені, тварина спочатку видаляється з поточного місця за допомогою методу
        // Після цього оновлюються координати
        setRow(newRow);
        setColumn(newColumn);
        IslandField.getInstance().addAnimal(this, newRow, newColumn);//Тварина додається на нове місце на острові методом
    }

    public int getStep() {
        return step;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getMaxHp() {
        return maxHp;
    }
}
