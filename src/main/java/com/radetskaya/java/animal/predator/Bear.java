package com.radetskaya.java.animal.predator;


import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.field.Location;

public class Bear extends Predator {
    /**
     * Конструктор класса Bear.
     * Устанавливает значения характеристик для медведя.
     */
    public Bear() {
        super(500, 2, 80, 5, "Bear");
    }

    /**
     * Получает шанс съесть определенный вид пищи.
     *
     * @param foodName Название пищи
     * @return Шанс съесть пищу
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
     * Размножается с партнером.
     * Если партнером является медведь, создается новый медведь на той же локации.
     *
     * @param partner Партнер для размножения
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