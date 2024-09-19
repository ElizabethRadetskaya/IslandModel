package com.radetskaya.java.animal.predator;


import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.field.Location;


public class Wolf extends Predator {
    public Wolf() {
        super(50, 3, 8, 30, "Wolf");
    }

    /**
     * Повертає шанс з'їсти вказану їжу для вовка.
     *
     * @param foodName Ім'я їжі
     * @return Шанс з'їсти їжу
     */
    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Horse", "Buffalo" -> 0.1;
            case "Deer", "WildBoar" -> 0.15;
            case "Duck" -> 0.4;
            case "Goat", "Rabbit" -> 0.6;
            case "Sheep" -> 0.7;
            case "Mouse" -> 0.8;
            default -> 0;
        };
    }

    /**
     * Метод для розмноження вовків.
     *
     * @param partner Партнер для размноження
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Wolf) {
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Wolf(), location.getRow(), location.getColumn());
        }
    }
}
