package com.radetskaya.java.simulation.thread;


import com.radetskaya.java.simulation.IslandSimulation;

/**
 * Реалізує задачу, яка відповідає за ріст рослин на острові.
 * Він реалізує інтерфейс Runnable, що дозволяє запускати задачу в окремому потоці.
 * Цей клас визначає, скільки рослин буде додано на острів залежно від часу, що пройшов у симуляції.
 */
public class PlantGrowthTask implements Runnable {
    @Override
    public void run() {
        int countPlants = 20;//кількість рослин, що може бути додана на острів за один цикл симуляції
        if (IslandSimulation.getInstance().getTimeNow() >= 2) {//Перевіряється, скільки часу пройшло з початку симуляції за допомогою методу
            //Якщо час симуляції більше або дорівнює 2 то на острів додається половина від початково встановленої кількості рослин (countPlants / 2). Це означає, що з часом ріст рослин може сповільнюватися
            IslandSimulation.getInstance().placePlants(countPlants / 2);
        } else {
            IslandSimulation.getInstance().placePlants(countPlants);
            //Якщо час симуляції менший за 2, додається повна кількість рослин (countPlants), що символізує активний ріст на початку симуляції.
        }
    }
}