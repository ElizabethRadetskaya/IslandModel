package com.radetskaya.java.animal.herbivore;


import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.field.Location;

public class Caterpillar extends Herbivore {
    /**
     * Конструктор класса Caterpillar.
     * Встановлює значення параметрів для гусениці.
     */
    public Caterpillar() {
        super(0.01, 0, 0, 1000, "Caterpillar");
    }

    /**
     * Метод розмноження гусениці.
     * Якщо партнер є гусеницею, створюється нова гусениця і додається на поле.
     *
     * @param partner Партнер для розмноження
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Caterpillar){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Caterpillar(), location.getRow(), location.getColumn());
        }
    }
}
