package com.radetskaya.java.plant;

// Базовий клас для рослин


import com.radetskaya.java.lifeform.LifeForm;

public class Plant extends LifeForm {
    /**
     * Конструктор класу Plant
     */
    public Plant() {
        super(1, 200, "Plant");
    }
}