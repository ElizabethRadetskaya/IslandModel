package com.radetskaya.java.animal.herbivore;


import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.field.Location;

public class Sheep extends Herbivore {
    /**
     * Конструктор класса Sheep.
     * Устанавливает значения характеристик для овцы.
     */
    public Sheep() {
        super(70, 3, 15, 140, "Sheep");
    }

    /**
     * Размножается с партнером.
     * Если партнером является овца, создается новая овца на той же локации.
     *
     * @param partner Партнер для размножения
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Sheep){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Sheep(), location.getRow(), location.getColumn());
        }
    }
}