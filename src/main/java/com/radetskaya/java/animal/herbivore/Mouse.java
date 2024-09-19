package com.radetskaya.java.animal.herbivore;


import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.field.Location;

public class Mouse extends Herbivore {
    /**
     * Конструктор класса Mouse.
     * Встановлює значення параметрів для миші.
     */
    public Mouse() {
        super(0.05, 1, 0.01, 500, "Mouse");
    }

    /**
     * Повертає шанс з'їсти певну їжу.
     *
     * @param foodName Назва їжі
     * @return Шанс з'їсти їжу
     */
    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Caterpillar" -> 0.9;
            case "Plant" -> 1;
            default -> 0;
        };
    }

    /**
     * Размноження с партнером.
     * Якщо партнером є миша, створюється нова миша на тій самій локації.
     *
     * @param partner Партнер для размноження
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Mouse){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Mouse(), location.getRow(), location.getColumn());
        }
    }
}
