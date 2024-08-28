package com.radetskaya.java.animal.predator;

import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.animal.herbivore.Herbivore;

/// Абстрактний клас для хижаків
abstract class Predator extends Animal {
    public Predator(String species, int hungerLimit, double moveChance, double reproductionChance) {
        super(species, hungerLimit, moveChance, reproductionChance);
    }

    @Override
    public void eat(Location location) {
        // Логіка хижака: пошук і з'їдання травоїдних
        for (Animal animal : location.animals) {
            if (animal instanceof Herbivore) {
                location.removeAnimal(animal);
                this.currentHunger = 0;
                return;
            }
        }
        this.currentHunger++;
    }
}
