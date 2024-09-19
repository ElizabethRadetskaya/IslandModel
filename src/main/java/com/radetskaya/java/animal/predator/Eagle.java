package com.radetskaya.java.animal.predator;


import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.field.Location;

public class Eagle extends Predator {
    /**
     * Конструктор класса Eagle.
     * Встановлює значення параметрів для орла.
     */
    public Eagle() {
        super(6, 3, 1, 20, "Eagle");
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
            case "Fox" -> 0.1;
            case "Duck" -> 0.8;
            case "Rabbit", "Mouse" -> 0.9;
            default -> 0;
        };
    }

    /**
     * Розмножується із партнером.
     * Якщо партнером є орел, створюється новий орел на тій самій локації.
     *
     * @param partner Партнер для размноження
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Eagle){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Eagle(), location.getRow(), location.getColumn());
        }
    }
}
