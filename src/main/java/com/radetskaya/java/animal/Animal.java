package com.radetskaya.java.animal;

import com.radetskaya.java.location.Island;

import javax.xml.stream.Location;

/**
 * @param species вид
 * @param weight вага
 * @param maxPopulation максимальна кількість населення
 * @param  maxMovement максимальний рух
 * @param foodNeeded Потрібна їжа
 * @param   currentHunger це поточний голод = 0
 */


public abstract class Animal {
    protected String species;
    protected double weight;
    protected int maxPopulation;
    protected int maxMovement;
    protected double foodNeeded;
    protected double currentHunger;

    public Animal(String species, double weight, int maxPopulation, int maxMovement, double foodNeeded) {
        this.species = species;
        this.weight = weight;
        this.maxPopulation = maxPopulation;
        this.maxMovement = maxMovement;
        this.foodNeeded = foodNeeded;
        this.currentHunger = 0;
    }

    public abstract void eat(Location location);
    public abstract void move(Island island, int currentX, int currentY);
    public abstract void reproduce(Location location);
    public abstract void die(Location location);

    // Додаткові методи
}