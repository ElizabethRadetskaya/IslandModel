package com.radetskaya.java.location;

import com.radetskaya.java.animal.herbivore.Rabbit;
import com.radetskaya.java.animal.predator.Wolf;

import java.util.Random;

public class Runner {

        public static void main(String[] args) {
            Island island = new Island(100, 20);
            int initialAnimals = 50;

            Random random = new Random();
            for (int i = 0; i < initialAnimals; i++) {
                int x = random.nextInt(100);
                int y = random.nextInt(20);
                // Додаємо різних тварин на острів
                if (random.nextBoolean()) {
                    island.getLocation(x, y).addAnimal(new Wolf());
                } else {
                    island.getLocation(x, y).addAnimal(new Rabbit());
                }
            }

            // Симуляція декількох кроків
            for (int i = 0; i < 100; i++) {
                island.step();
            }
        }
    }

