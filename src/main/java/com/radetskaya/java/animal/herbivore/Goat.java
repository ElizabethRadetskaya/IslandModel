package com.radetskaya.java.animal.herbivore;

import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.field.Location;


public class Goat extends Herbivore {
    /**
     * Конструктор класу Goat.
     * Встановлює значення параметрів для кози.
     */
    public Goat() {
        super(60, 3, 10, 140, "Goat");
    }

    /**
     * Метод розмноження кози.
     * Якщо партнер є козою, створюється нова коза і додається на поле.
     *
     * @param partner Партнер для размноження
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Goat){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Goat(), location.getRow(), location.getColumn());
        }
    }
}
