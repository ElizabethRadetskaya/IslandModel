package com.radetskaya.java.simulation.thread.animalLifecycleTask.task;


import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.field.IslandField;

import java.util.List;

/**
 * Задача для переміщення тварин на острові
 */
public class AnimalMoveTask implements Runnable {//Клас імплементує інтерфейс Runnable, що дозволяє запускати його в окремому потоці
    @Override
    public void run() {
        List<Animal> animals = IslandField.getInstance().getAllAnimals().stream().filter(c -> c.getStep() > 0).toList();
        //Викликається метод IslandField.getInstance().getAllAnimals(), щоб отримати всіх тварин, які знаходяться на острові.
        //Фільтруються тільки ті тварини, у яких кількість доступних кроків більше нуля (c.getStep() > 0). Це дозволяє виключити тварин, які в цей момент не можуть або не повинні переміщуватися.
        for (Animal animal : animals) {
            animal.move();//Для кожної тварини, що залишилася після фільтрації, викликається метод move(), який відповідає за безпосереднє переміщення тварини на нове місце на острові.
        }
    }
}