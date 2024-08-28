package com.radetskaya.java.animal.herbivore;

import com.radetskaya.java.animal.Animal;

// Абстрактний клас для травоїдних
abstract class Herbivore extends Animal {
    public Herbivore(String species, int hungerLimit, double moveChance, double reproductionChance) {
        super(species, hungerLimit, moveChance, reproductionChance);
    }

    @Override
    public void eat(Location location) {
        // Логіка травоїдних: поїдання рослин
        if (location.plants > 0) {
            location.plants--;
            this.currentHunger = 0;
        } else {
            this.currentHunger++;
        }
    }
}