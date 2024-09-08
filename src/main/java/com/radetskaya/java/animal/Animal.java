package com.radetskaya.java.animal;

import com.radetskaya.java.location.Cell;
import com.radetskaya.java.location.Simulation;

// Абстрактный класс Animal
public abstract class Animal {
    protected double weight;
    protected int movementSpeed;
    protected int maxOnCell;
    protected double foodNeeded;
    protected Cell currentCell;
    protected int currentX;
    protected int currentY;

    public Animal(double weight, int movementSpeed, int maxOnCell, double foodNeeded) {
        this.weight = weight;
        this.movementSpeed = movementSpeed;
        this.maxOnCell = maxOnCell;
        this.foodNeeded = foodNeeded;
    }
    public double getWeight() {
        return weight;
    }


    public abstract void move();
    public abstract void eat();
    public abstract void reproduce();

    protected void moveTo(int newX, int newY) {
        // Логика перемещения на новое место
        currentX = newX;
        currentY = newY;
        // Обновить текущую клетку
        currentCell.removeAnimal(this);
        currentCell = Simulation.getCell(newX, newY); // Получить новую клетку
        currentCell.addAnimal(this);
    }

    protected void feed(double foodAmount) {
        // Логика насыщения
    }
}
