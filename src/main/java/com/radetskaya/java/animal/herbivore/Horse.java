package com.radetskaya.java.animal.herbivore;


import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.field.Location;

public class Horse extends Herbivore {
    /**
     * Конструктор класу Horse.
     * Встановлює значення параметрів для коня.
     */
    public Horse() {
        super(400, 4, 60, 20, "Horse");
    }

    /**
     * Размноження с партнером.
     * Якщо партнером є кінь, створюється новий кінь на тій самій локації.
     *
     * @param partner Партнер для размноження
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Horse){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Horse(), location.getRow(), location.getColumn());
        }
    }
}
