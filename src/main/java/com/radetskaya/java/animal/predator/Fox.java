package com.radetskaya.java.animal.predator;

import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.field.Location;

public class Fox extends Predator {
    /**
     * Конструктор класса Fox.
     * Встановлює значення параметрів для лисиці.
     */
    public Fox() {
        super(8, 2, 2, 30, "Fox");
    }

    /**
     * Має шанс з'їсти певний вид їжі.
     *
     * @param foodName Назва їжі
     * @return Шанс з'їсти їжу
     */
    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Caterpillar" -> 0.4;
            case "Duck" -> 0.6;
            case "Rabbit" -> 0.7;
            case "Mouse" -> 0.9;
            default -> 0;
        };
    }

    /**
     * Розмножується з партнером.
     * Якщо партнером є лисиця, створюється нова лисиця на тій самій локації.
     *
     * @param partner Партнер для размноження
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Fox){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Fox(), location.getRow(), location.getColumn());
        }
    }
}
