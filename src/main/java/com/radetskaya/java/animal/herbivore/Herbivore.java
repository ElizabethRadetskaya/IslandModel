package com.radetskaya.java.animal.herbivore;


import com.radetskaya.java.animal.Animal;

public abstract class Herbivore extends Animal {
    /**
     * Конструктор класса Herbivore.
     * Встановлює значення характеристик для травоїдної тварини.
     *
     * @param weight        Вага тварини
     * @param step          Крок пересування тварини
     * @param maxHp         Максимальна кількість очок здоров'я тварини
     * @param maxPopulation Максимальна кількість тварин даного виду на острові
     * @param name          Назва виду тварини
     */
    public Herbivore(double weight, int step, double maxHp, int maxPopulation, String name) {
        super(weight, step, maxHp, maxPopulation, name);
    }

    /**
     * Отримує ймовірність поїдання певного виду їжі.
     * Для травоїдних тварин ймовірність поїдання рослин дорівнює 1, для решти видів їжі ймовірність дорівнює 0.
     *
     * @param foodName Назва виду їжі
     * @return Імовірність поїдання їжі
     */
    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Plant" -> 1;
            default -> 0;
        };
    }
}
