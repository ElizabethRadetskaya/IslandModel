package com.radetskaya.java.animal.herbivore;


import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.field.Location;

public class Sheep extends Herbivore {
    /**
     * Конструктор класса Sheep.
     * Встановлює значення параметрів для вівці.
     */
    public Sheep() {
        super(70, 3, 15, 140, "Sheep");
    }

    /**
     * Розмножується із партнером.
     * Якщо партнером є вівця, створюється нова вівця на тій самій локації.
     *
     * @param partner Партнер для размноження
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Sheep){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Sheep(), location.getRow(), location.getColumn());
        }
    }
}
