package com.radetskaya.java.animal.herbivore;

import com.radetskaya.java.animal.Animal;

abstract class Herbivore extends Animal {
    public Herbivore(double weight, int maxOnCell, int movementSpeed, double foodNeeded) {
        super(weight, maxOnCell, movementSpeed, foodNeeded);
    }
}