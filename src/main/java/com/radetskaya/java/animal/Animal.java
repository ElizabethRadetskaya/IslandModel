package com.radetskaya.java.animal;

import javax.xml.stream.Location;

/// Абстрактний клас для тварин
public abstract class Animal {
    String species;
    int hungerLimit; // Максимальна кількість кроків, які тварина може витримати без їжі
    int currentHunger; // Поточний рівень голоду
    double moveChance; // Імовірність руху
    double reproductionChance; // Імовірність розмноження

    public Animal(String species, int hungerLimit, double moveChance, double reproductionChance) {
        this.species = species;
        this.hungerLimit = hungerLimit;
        this.currentHunger = 0;
        this.moveChance = moveChance;
        this.reproductionChance = reproductionChance;
    }

    // Загальні методи для тварин
    public abstract void eat(Location location);
    public abstract void move(Island island, int currentX, int currentY);
    public abstract void reproduce(Location location);
    public abstract void die(Location location);ation location);//помирати
}