package com.radetskaya.java.animal.herbivore;

import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.field.Location;


public class Deer extends Herbivore {
    /**
     * Конструктор класу Deer.
     * Устанавливает значения характеристик для оленя.
     */
    public Deer() {
        super(300, 4, 50, 20, "Deer");
    }

    /**
     * Метод розмноження оленя.
     * Якщо партнер є оленем, створюється новий олень і додається на поле.
     *
     * @param partner Партнер для размноження
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Deer){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Deer(), location.getRow(), location.getColumn());
        }
    }
}
