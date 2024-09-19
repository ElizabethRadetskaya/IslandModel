package com.radetskaya.java.animal.herbivore;


import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.field.Location;

public class Duck extends Herbivore {
    /**
     * Конструктор класса Duck.
     * Устанавливает значения характеристик для утки.
     */
    public Duck() {
        super(1, 4, 0.15, 200, "Duck");
    }

    /**
     * Метод, що повертає можливість поїдання певної їжі.
     *
     * @param foodName Название пищи
     * @return Імовірність поїдання
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
     * Метод розмноження качки.
     * Якщо партнер є качкою, створюється нова качка і додається на поле.
     *
     * @param partner Партнер для размноження
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Duck){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Duck(), location.getRow(), location.getColumn());
        }
    }
}
