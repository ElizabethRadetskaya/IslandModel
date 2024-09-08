package com.radetskaya.java.plant;

// Базовий клас для рослин
public class Plant {
    double weight;

    public Plant() {
        this.weight = 1.0;  // или другое значение по умолчанию
    }

    public Plant(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }
}