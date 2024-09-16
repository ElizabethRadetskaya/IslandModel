package com.radetskaya.java.animal;


import com.radetskaya.java.error.ObjectNotLifeFormException;
import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.field.Location;
import com.radetskaya.java.lifeform.LifeForm;
import com.radetskaya.java.plant.Plant;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends LifeForm {
    private final int step; // Скорость перемещения, не более чем, клеток за ход
    private final double maxHp; // Максимальное количество килограммов пищи нужно животному для полного насыщения
    private double hp; // Количество здоровья животного

    public Animal(double weight, int step, double maxHp, int maxPopulation, String name) {
        super(weight, maxPopulation, name);
        this.step = step;
        this.maxHp = maxHp;
        this.hp = maxHp; // На старте максимальное количество здоровья
    }

    /**
     * Проверяет, может ли животное съесть указанную пищу.
     *
     * @param food Пища, которую пытается съесть животное
     * @return true, если животное успешно съело пищу, иначе - false
     */
    public boolean eat(Object food) {
        double chanceToEat;
        LifeForm lifeForm = null;
        boolean animalEatFood;

        if (food instanceof LifeForm) {
            lifeForm = (LifeForm) food;
        } else {
            try {
                throw new ObjectNotLifeFormException("Объект не является животным/растением.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        String foodName = lifeForm.getName();
        chanceToEat = getChanceToEat(foodName);

        animalEatFood = ThreadLocalRandom.current().nextDouble() < chanceToEat;
        if (animalEatFood) {
            setHp(Math.min((getHp() + lifeForm.getWeight()), getMaxHp())); // Показатель здоровья повышается после съедения
            Location location = IslandField.getInstance().getLocation(lifeForm.getRow(), lifeForm.getColumn()); // Животное/растение удаляется из списка обиталей локации после съедения
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
     * Абстрактный метод для получения шанса съесть указанную пищу.
     *
     * @param foodName Имя пищи
     * @return Шанс съесть пищу
     */
    public abstract double getChanceToEat(String foodName);

    /**
     * Абстрактный метод для размножения животного с партнером.
     *
     * @param partner Партнер для размножения
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