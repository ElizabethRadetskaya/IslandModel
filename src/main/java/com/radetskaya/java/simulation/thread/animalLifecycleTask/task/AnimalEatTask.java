package com.radetskaya.java.simulation.thread.animalLifecycleTask.task;


import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.field.Location;
import com.radetskaya.java.lifeform.LifeForm;
import com.radetskaya.java.plant.Plant;
import com.radetskaya.java.simulation.IslandSimulation;
import com.radetskaya.java.simulation.thread.StatisticsTask;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Задача для процесу харчування тварин на острові
 */
public class AnimalEatTask implements Runnable {
    private final CountDownLatch latch;
    private int animalsEaten;

    /**
     * Конструктор класса
     * @param latch Счетчик CountDownLatch для синхронизации потоков
     */
    public AnimalEatTask(CountDownLatch latch) {
        this.latch = latch;//конструктор Приймає об’єкт latch і ініціалізує його для синхронізації потоків.
    }

    @Override
    public void run() {//Цей метод викликається при виконанні задачі в окремому потоці і відповідає за процес харчування тварин.
        animalsEaten = 0;//лічильник тварин, яких було з’їдено іншими тваринами встановлюється на 0
        List<Animal> animals = IslandField.getInstance().getAllAnimals();//Отримується список всіх тварин на острові
        List<LifeForm> lifeFormsEaten = new ArrayList<>();//Створюється список lifeFormsEaten, який буде містити всі форми життя (тварини або рослини), які вже були з’їдені.
        int countAnimalsStart = animals.size();

        //Перевірка наявності тварин для харчування:
        if (countAnimalsStart > 0 && animals.stream().filter(c -> !(c.getName().equals("Caterpillar"))).toList().size() > 0) {
            Iterator<Animal> iterator = animals.iterator();

            while (iterator.hasNext()) {//Цикл харчування тварин
                Animal currentAnimal = iterator.next();
                if (currentAnimal.getMaxHp() > 0) {//Для кожної тварини перевіряється, чи здатна вона до харчування (її максимальне здоров’я більше 0).
                    Location location = IslandField.getInstance().getLocation(currentAnimal.getRow(), currentAnimal.getColumn());//Отримується місце розташування тварини на острові за допомогою координат.
                    List<LifeForm> locationLifeForms = location.getLifeForms();//З цього місця беруться всі наявні форми життя, включаючи інші тварини та рослини.

                    if (!locationLifeForms.isEmpty()) {//Харчування тварин
                        for (LifeForm lifeForm : locationLifeForms) {
                            if (currentAnimal.getChanceToEat(lifeForm.getName()) > 0 && !(lifeFormsEaten.contains(lifeForm))) {
                                //Для кожної тварини перевіряється, чи може вона з’їсти певну форму життя. Це визначається на основі шансу харчування (getChanceToEat(lifeForm.getName())).
                                boolean isEaten = currentAnimal.eat(lifeForm);//Якщо тварина може з’їсти іншу тварину або рослину, то викликається метод eat(), який симулює процес харчування.

                                if (isEaten) {
                                    if (lifeForm instanceof Animal animalEaten) {
                                        if (location.getAnimals().contains(animalEaten)) {
                                            IslandField.getInstance().removeAnimal(animalEaten, location.getRow(), location.getColumn());
                                        }//Якщо тварина з’їдає іншу тварину, вона видаляється з поля за допомогою IslandField.getInstance().removeAnimal()
                                        lifeFormsEaten.add(animalEaten);
                                        animalsEaten++;//кількість з’їдених тварин збільшується.
                                    } else {
                                        Plant plant = (Plant) lifeForm;
                                        if (location.getPlants().size() > 0) {
                                            IslandField.getInstance().removePlant(plant, location.getRow(), location.getColumn());
                                        }//Якщо тварина з’їдає рослину, вона також видаляється за допомогою removePlant()
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                iterator.remove();
            }
        } else if (countAnimalsStart == 0) {//Якщо всі тварини на острові помирають, симуляція завершується з повідомленням про програш
            System.out.printf("ВИ ПРОГРАЛИ! ВСІ ТВАРИННІ ПОМЕРЛИ НА %d ДЕНЬ!", StatisticsTask.getCurrentDay());
            IslandSimulation.getInstance().getExecutorService().shutdown();
            System.exit(0);
        } else {//Якщо залишилися тільки гусениці (які не можуть бути з’їдені іншими тваринами через свою “безсмертність”)їм їжа не потрібна, симуляція також завершується з повідомленням про їхню перемогу
            System.out.printf("ПЕРЕМОЖИЛИ ГУСЕНИЦІ! У ЖИВИХ ЗАЛИШИЛИСЯ ТІЛЬКИ ВОНИ НА %d ДЕНЬ!!", StatisticsTask.getCurrentDay());
            IslandSimulation.getInstance().getExecutorService().shutdown();
            System.exit(0);
        }
        latch.countDown();
    }
    public int getAnimalsEaten() {
        return animalsEaten;//Повертає кількість тварин, яких було з’їдено під час виконання завдання.
    }
}