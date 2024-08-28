package com.radetskaya.java.animal.herbivore;

import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.plant.Plant;

import javax.xml.stream.Location;

// Абстрактний клас для травоїдних
public abstract class Herbivore extends Animal {
    public Herbivore(String species, double weight, int maxPopulation, int maxMovement, double foodNeeded) {
        super(species, weight, maxPopulation, maxMovement, foodNeeded);
    }

    @Override
    public void eat(Location location) {
        for (Plant plant : location.getPlants()) {
            location.removePlant(plant);
            this.currentHunger = 0;
            return;
        }
        this.currentHunger++;
    }
}