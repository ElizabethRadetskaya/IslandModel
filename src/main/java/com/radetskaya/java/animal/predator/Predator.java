package com.radetskaya.java.animal.predator;

import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.animal.herbivore.Herbivore;
import com.radetskaya.java.lifeform.FeedingProbability;

import javax.xml.stream.Location;

/// Абстрактний клас для хижаків
public abstract class Predator extends Animal {
    public Predator(String species, double weight, int maxPopulation, int maxMovement, double foodNeeded) {
        super(species, weight, maxPopulation, maxMovement, foodNeeded);
    }

    @Override
    public void eat(Location location) {
        for (Animal animal : location.getAnimals()) {
            if (animal instanceof Herbivore && FeedingProbability.canEat(this, animal)) {
                location.removeAnimal(animal);
                this.currentHunger = 0;
                return;
            }
        }
        this.currentHunger++;
    }
}
