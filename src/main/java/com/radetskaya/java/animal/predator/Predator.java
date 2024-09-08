package com.radetskaya.java.animal.predator;

import com.radetskaya.java.animal.Animal;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Predator extends Animal {
    public Predator(double weight, int movementSpeed, int maxOnCell, double foodNeeded) {
        super(weight, movementSpeed, maxOnCell, foodNeeded);
    }

    @Override
    public void eat() {
        List<Animal> animalsInCell = currentCell.getAnimals();
        for (Animal prey : animalsInCell) {
            if (canPreyOn(prey)) {
                int chance = getHuntingProbability(prey);
                if (ThreadLocalRandom.current().nextInt(100) < chance) {
                    animalsInCell.remove(prey);
                    this.feed(prey.getWeight()); //  метод для получения веса
                    break;
                }
            }
        }
    }

    private boolean canPreyOn(Animal prey) {
        // Логика определения, может ли данный хищник съесть это животное
        return true;
    }

    private int getHuntingProbability(Animal prey) {
        // Логика получения вероятности охоты на добычу
        return 0;
    }
}