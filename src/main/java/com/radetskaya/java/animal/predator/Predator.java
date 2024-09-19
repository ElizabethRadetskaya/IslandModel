package com.radetskaya.java.animal.predator;

import com.radetskaya.java.animal.Animal;

public abstract class Predator extends Animal {

    /**
     * Конструктор класса Predator.
     *
     * @param weight        Вага хижака
     * @param step          Крок переміщення
     * @param maxHp         Максимальна кількість здоров'я
     * @param maxPopulation Максимальна кількість особин
     * @param name          Назва хижака
     */
    public Predator(double weight, int step, double maxHp, int maxPopulation, String name) {
        super(weight, step, maxHp, maxPopulation, name);
    }
}
