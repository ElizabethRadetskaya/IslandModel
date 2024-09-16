package com.radetskaya.java.simulation.thread.animalLifecycleTask.task;


import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.field.Location;
import com.radetskaya.java.simulation.IslandSimulation;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Завдання для зменшення здоров'я тварин на острові
 */
//Завдання цього класу полягає в тому, щоб зменшувати здоров’я тварин на острові
// на певний відсоток через голод, і видаляти тих тварин, чиє здоров’я стало меншим
// або рівним нулю.
public class AnimalHpDecreaseTask implements Runnable {//реалізує інтерфейс Runnable
    private double percentOfHpToDecrease = 15;//відсоток здоров'я за замовчуванням
    private final CountDownLatch latch;//об’єкт типу CountDownLatch, який синхронізує виконання потоків. Він використовується для координації, щоб інші потоки могли дочекатися завершення цієї задачі.
    private int animalsDiedByHungry;//лічильник тварин, які померли від голоду під час виконання завдання.

    /**
     * Конструктор класу
     * @param latch Счетчик CountDownLatch для синхронизации потоков
     */
    public AnimalHpDecreaseTask(CountDownLatch latch) {
        this.latch = latch;//Конструктор класу приймає latch, який використовується для синхронізації потоків
    }

    @Override
    public void run() {
        animalsDiedByHungry = 0;//Лічильник тварин, що померли від голоду, встановлюється на 0.
        List<Animal> animals = IslandField.getInstance().getAllAnimals().stream().filter(c -> c.getMaxHp() > 0).toList();
        //Отримується список всіх тварин на острові, фільтруючи тих, у кого максимальне здоров’я більше 0 (getMaxHp() > 0). Це робиться за допомогою стрім-фільтрації.
        if (IslandSimulation.getInstance().getTimeNow() / 60 >= 3) {
            //Якщо симуляція триває більше 3 годин (IslandSimulation.getInstance().getTimeNow() / 60 >= 3), відсоток зниження здоров’я подвоюється.
            percentOfHpToDecrease = percentOfHpToDecrease * 2;
        }
        for (Animal animal : animals) {//Цикл зменшення здоров’я
            double hpToDecrease = animal.getMaxHp() * percentOfHpToDecrease / 100.0;
            if (animal.getHp() - hpToDecrease > 0) {
                animal.setHp(animal.getHp() - hpToDecrease);//Для кожної тварини в списку розраховується кількість здоров’я, яке потрібно відняти (hpToDecrease), як відсоток від максимального здоров’я тварини.Якщо після зменшення здоров’я воно залишається більше 0, здоров’я тварини просто зменшується.
            } else {//Якщо здоров’я падає до 0 або нижче, тварина видаляється з острова за допомогою методу IslandField.getInstance().removeAnimal, і лічильник тварин, що померли від голоду, збільшується.
                Location location = IslandField.getInstance().getLocation(animal.getRow(), animal.getColumn());
                IslandField.getInstance().removeAnimal(animal, location.getRow(), location.getColumn());
                animalsDiedByHungry++;//лічильник тварин, що померли від голоду, збільшується
            }
        }
        latch.countDown();
    }
    public int getAnimalsDiedByHungry() {
        return animalsDiedByHungry;//Цей метод повертає кількість тварин, які померли від голоду під час виконання задачі.
    }
}