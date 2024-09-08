package com.radetskaya.java.animal.herbivore;

import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.plant.Plant;

import java.util.List;

public abstract class Herbivore extends Animal {
    public Herbivore(double weight, int movementSpeed, int maxOnCell, double foodNeeded) {
        super(weight, movementSpeed, maxOnCell, foodNeeded);
    }

    @Override
    public void eat() {
        List<Plant> plantsInCell = currentCell.getPlants();
        if (!plantsInCell.isEmpty()) {
            Plant plant = plantsInCell.get(0); // Травоядное съедает растение
            plantsInCell.remove(plant);
            this.feed(plant.getWeight());
        }
    }
}