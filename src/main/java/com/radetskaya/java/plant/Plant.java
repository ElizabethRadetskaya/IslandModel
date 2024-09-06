package com.radetskaya.java.plant;

// Базовий клас для рослин
public class Plant {
    double weight;

    public Plant() {
        this.weight = 1.0; // Например, по умолчанию вес 1.0 кг
    }

    public Plant(double weight) {
        this.weight = weight;
    }
}
