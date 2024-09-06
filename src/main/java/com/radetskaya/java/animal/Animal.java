package com.radetskaya.java.animal;

// Абстрактный класс Animal
public abstract class Animal {
    double weight;
    int maxOnCell;
    int movementSpeed;
    double foodNeeded;

    public Animal(double weight, int maxOnCell, int movementSpeed, double foodNeeded) {
        this.weight = weight;
        this.maxOnCell = maxOnCell;
        this.movementSpeed = movementSpeed;
        this.foodNeeded = foodNeeded;
    }

    public abstract void move();
    public abstract void eat();
    public abstract void reproduce();
}
