package com.radetskaya.java.animal.predator;

import com.radetskaya.java.animal.Animal;

abstract class Predator extends Animal {
    public Predator(double weight, int maxOnCell, int movementSpeed, double foodNeeded) {
        super(weight, maxOnCell, movementSpeed, foodNeeded);
    }
}