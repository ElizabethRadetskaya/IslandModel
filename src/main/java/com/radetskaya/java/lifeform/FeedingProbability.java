package com.radetskaya.java.lifeform;

import com.radetskaya.java.animal.Animal;

import java.util.concurrent.ThreadLocalRandom;

// Таблиця ймовірностей
class FeedingProbability {
    private static final int[][] PROBABILITY_TABLE = {
            //   Вовк Удав Лисиця Ведмідь Орел Кінь Олень Кролик Миша Коза Вівця Кабан Буйвол Качка Гусінь Рослини
            /*Вовк*/   {-1,  0,  0,  0,  0, 10, 15, 60, 80, 60, 70, 15, 10, 40,  0,  0},
            /*Удав*/   { 0, -1, 15,  0,  0,  0,  0, 20, 40,  0,  0,  0,  0, 10,  0,  0},
            /*Лисиця*/ { 0,  0, -1,  0,  0,  0,  0, 70, 90,  0,  0,  0,  0, 60, 40,  0},
            /*Ведмідь*/{ 0, 80,  0, -1,  0, 40, 80, 80, 90, 70, 70, 50, 20, 10,  0,  0},
            /*Орел*/   { 0,  0, 10,  0, -1,  0,  0, 90, 90,  0,  0,  0,  0, 80,  0,  0},
            /*Кінь*/   { 0,  0,  0,  0,  0, -1,  0,  0,  0,  0,  0,  0,  0,  0,  0, 100},
            /*Олень*/  { 0,  0,  0,  0,  0,  0, -1,  0,  0,  0,  0,  0,  0,  0,  0, 100},
            /*Кролик*/ { 0,  0,  0,  0,  0,  0,  0, -1,  0,  0,  0,  0,  0,  0,  0, 100},
            /*Миша*/   { 0,  0,  0,  0,  0,  0,  0,  0, -1,  0,  0,  0,  0,  0, 90, 100},
            /*Коза*/   { 0,  0,  0,  0,  0,  0,  0,  0,  0, -1,  0,  0,  0,  0,  0, 100},
            /*Вівця*/  { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0, -1,  0,  0,  0,  0, 100},
            /*Кабан*/  { 0,  0,  0,  0,  0,  0,  0,  0, 50,  0,  0, -1,  0,  0, 90, 100},
            /*Буйвол*/ { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, -1,  0,  0, 100},
            /*Качка*/  { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, -1, 90, 100},
            /*Гусінь*/ { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, -1, 100},
            /*Рослини*/{ 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, -1}
    };

    public static boolean canEat(Animal predator, Animal prey) {
        int predatorIndex = getAnimalIndex(predator);//хижак
        int preyIndex = getAnimalIndex(prey);//здобич

        if (predatorIndex == -1 || preyIndex == -1) return false;

        int probability = PROBABILITY_TABLE[predatorIndex][preyIndex];
        if (probability == -1) return false; // Немає ймовірності поїдання

        return ThreadLocalRandom.current().nextInt(100) < probability;
    }

    private static int getAnimalIndex(Animal animal) {
        // Індексування відповідно до порядку в таблиці
        switch (animal.species) {
            case "Wolf": return 0;
            case "Boa": return 1;
            case "Fox": return 2;
            case "Bear": return 3;
            case "Eagle": return 4;
            case "Horse": return 5;
            case "Deer": return 6;
            case "Rabbit": return 7;
            case "Mouse": return 8;
            case "Goat": return 9;
            case "Sheep": return 10;
            case "Boar": return 11;
            case "Buffalo": return 12;
            case "Duck": return 13;
            case "Caterpillar": return 14;
            case "Plant": return 15;
            default: return -1;
        }
    }
}