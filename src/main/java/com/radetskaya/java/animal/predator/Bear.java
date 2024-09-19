package com.radetskaya.java.animal.predator;


import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.field.Location;

public class Bear extends Predator {
    /**
     * Конструктор класса Bear.
     * Встановлює значення параметрів для ведмедя.
     */
    public Bear() {
        super(500, 2, 80, 5, "Bear");
    }

    /**
     * Має шанс з'їсти певний вид їжі.
     *
     * @param foodName Назва їжі
     * @return Шанс з'їсти їжу
     */
    @Override
    public double getChanceToEat(String foodName) {
        return switch (foodName) {
            case "Duck" -> 0.1;
            case "Buffalo" -> 0.2;
            case "Horse" -> 0.4;
            case "WildBoar" -> 0.5;
            case "Goat", "Sheep" -> 0.7;
            case "Deer", "Rabbit", "Snake" -> 0.8;
            case "Mouse" -> 0.9;
            default -> 0;
        };
    }

    /**
     * Розмножується із партнером.
     * Якщо партнером є ведмідь, створюється новий ведмідь на тій самій локації.
     *
     * @param partner Партнер для размноження
     */
    @Override
    public void multiply(Animal partner) {
        if (partner instanceof Bear){
            Location location = IslandField.getInstance().getLocation(partner.getRow(), partner.getColumn());
            IslandField.getInstance().addAnimal(new Bear(), location.getRow(), location.getColumn());
        }//Оператор instanceof перевіряє, чи є передана тварина (партнер для розмноження) ведмедем (Bear).
        // Якщо це так, то метод продовжує виконувати логіку для розмноження саме цього виду.
        //Якщо партнер є ведмедем, то визначається його місце на острові за допомогою методу IslandField.getInstance().getLocation(partner.getRow(),
        // partner.getColumn()). Це дозволяє знайти координати, де саме знаходиться ведмідь на острові.
        //Після того, як місце розташування ведмедя визначено, створюється новий екземпляр класу Bear, який представляє новонародженого ведмедя.
        // Цей новий ведмідь додається на острів у те саме місце (координати), де знаходиться його батько або мати, використовуючи метод IslandField.getInstance().addAnimal(new Bear(), location.getRow(), location.getColumn()).
    }
}
