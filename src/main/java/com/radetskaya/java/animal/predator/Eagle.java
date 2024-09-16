package com.radetskaya.java.animal.predator;


import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.field.Location;

public class Eagle extends Predator {
    /**
     * Конструктор класса Eagle.
     * Устанавливает значения характеристик для орла.
     */
    public Eagle() {
        super(6, 3, 1, 20, "Eagle");
    }

    /**
     * Получает шанс съесть определенный вид пищи.
     *
     * @param foodName Название пищи
     * @return Шанс съесть пищу
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
     * Размножается с партнером.
     * Если партнером является орел, создается новый орел на той же локации.
     *
     * @param partner Партнер для размножения
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Eagle){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Eagle(), location.getRow(), location.getColumn());
        }
    }
}