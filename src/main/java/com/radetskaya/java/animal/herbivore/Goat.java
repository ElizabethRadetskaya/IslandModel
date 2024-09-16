package com.radetskaya.java.animal.herbivore;

import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.field.Location;


public class Goat extends Herbivore {
    /**
     * Конструктор класса Goat.
     * Устанавливает значения характеристик для козы.
     */
    public Goat() {
        super(60, 3, 10, 140, "Goat");
    }

    /**
     * Метод размножения козы.
     * Если партнер является козой, то создается новая коза и добавляется на поле.
     *
     * @param partner Партнер для размножения
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Goat){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Goat(), location.getRow(), location.getColumn());
        }
    }
}