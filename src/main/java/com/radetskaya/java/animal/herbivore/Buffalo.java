package com.radetskaya.java.animal.herbivore;


import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.field.Location;

public class Buffalo extends Herbivore {
    /**
     * Конструктор класса Buffalo.
     * Встановлює значення параметрів для буйвола.
     */
    public Buffalo() {
        super(700, 3, 100, 10, "Buffalo");
    }

    /**
     * Метод розмноження буйвола.
     * Якщо партнер є буйволом, створюється новий буйвол і додається на поле.
     *
     * @param partner Партнер для размноження
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Buffalo){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Buffalo(), location.getRow(), location.getColumn());
        }
    }
}
