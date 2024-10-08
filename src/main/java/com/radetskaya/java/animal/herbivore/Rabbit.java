package com.radetskaya.java.animal.herbivore;


import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.field.Location;

public class Rabbit extends Herbivore {
    /**
     * Конструктор класса Rabbit.
     * Устанавливает значения характеристик для кролика.
     */
    public Rabbit() {
        super(2, 2, 0.45, 150, "Rabbit");
    }

    /**
     * Розмножується із партнером.
     * Якщо партнером є кролик, створюється новий кролик на тій самій локації.
     *
     * @param partner Партнер для размноження
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Rabbit){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Rabbit(), location.getRow(), location.getColumn());
        }
    }
}
