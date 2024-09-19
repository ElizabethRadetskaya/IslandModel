package com.radetskaya.java.animal.herbivore;


import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.field.Location;

public class WildBoar extends Herbivore {
    /**
     * Конструктор класса WildBoar.
     * Встановлює значення параметрів для дикої свині.
     */
    public WildBoar() {
        super(400, 2, 50, 50, "WildBoar");
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
            case "Mouse" -> 0.5;
            case "Caterpillar" -> 0.9;
            case "Plant" -> 1;
            default -> 0;
        };
    }

    /**
     * Розмножується із партнером.
     * Якщо партнером є дика свиня, створюється нова дика свиня на тій самій локації.
     *
     * @param partner Партнер для размноження
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof WildBoar){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new WildBoar(), location.getRow(), location.getColumn());
        }
    }
}
