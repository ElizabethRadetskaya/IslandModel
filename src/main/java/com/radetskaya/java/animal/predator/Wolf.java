package com.radetskaya.java.animal.predator;


import com.radetskaya.java.lifeform.FeedingProbability;
import com.radetskaya.java.location.Island;

import javax.xml.stream.Location;

// Конкретні хижаки
public class Wolf extends Predator {
    public Wolf() {
        super("Wolf", 50, 30, 3, 8);
    }

    @Override
    public void move(Island island, int currentX, int currentY) {
        // Реалізація переміщення вовка
    }

    @Override
    public void reproduce(Location location) {
        // Реалізація розмноження вовка
    }

    @Override
    public void die(Location location) {
        // Реалізація смерті вовка
    }
}