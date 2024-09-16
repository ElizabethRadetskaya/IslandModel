package com.radetskaya.java.animal.herbivore;


import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.field.Location;

public class Horse extends Herbivore {
    /**
     * Конструктор класса Horse.
     * Устанавливает значения характеристик для лошади.
     */
    public Horse() {
        super(400, 4, 60, 20, "Horse");
    }

    /**
     * Размножается с партнером.
     * Если партнером является лошадь, создается новая лошадь на той же локации.
     *
     * @param partner Партнер для размножения
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Horse){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Horse(), location.getRow(), location.getColumn());
        }
    }
}