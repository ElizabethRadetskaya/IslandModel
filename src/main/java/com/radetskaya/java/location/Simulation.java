package com.radetskaya.java.location;

import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.plant.Plant;

import com.radetskaya.java.animal.predator.Wolf;
import com.radetskaya.java.animal.predator.Python;
import com.radetskaya.java.animal.predator.Fox;
import com.radetskaya.java.animal.predator.Eagle;
import com.radetskaya.java.animal.predator.Bear;
import com.radetskaya.java.animal.herbivore.Boar;
import com.radetskaya.java.animal.herbivore.Buffalo;
import com.radetskaya.java.animal.herbivore.Caterpillar;
import com.radetskaya.java.animal.herbivore.Deer;
import com.radetskaya.java.animal.herbivore.Duck;
import com.radetskaya.java.animal.herbivore.Goat;
import com.radetskaya.java.animal.herbivore.Horse;
import com.radetskaya.java.animal.herbivore.Mouse;
import com.radetskaya.java.animal.herbivore.Rabbit;
import com.radetskaya.java.animal.herbivore.Sheep;
import com.radetskaya.java.plant.Plant;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class Simulation {
    private final int gridWidth = 100;
    private final int gridHeight = 20;
    private final Cell[][] grid = new Cell[gridWidth][gridHeight];
    private final List<Animal> animals = new ArrayList<>();
    private final List<Plant> plants = new ArrayList<>();
    private final int stepsPerIteration = 10; // Примерное количество шагов за итерацию

    public Simulation() {
        initializeGrid();
        populateGrid();
    }

    private void initializeGrid() {
        for (int x = 0; x < gridWidth; x++) {
            for (int y = 0; y < gridHeight; y++) {
                grid[x][y] = new Cell();
            }
        }
    }

    private void populateGrid() {
        // Количество животных и растений для размещения
        int numberOfWolves = 10;
        int numberOfPythons = 5;
        int numberOfFoxes = 7;
        int numberOfBears = 3;
        int numberOfEagles = 8;
        int numberOfHorses = 5;
        int numberOfDeers = 6;
        int numberOfRabbits = 50;
        int numberOfMice = 30;
        int numberOfGoats = 4;
        int numberOfSheep = 4;
        int numberOfBoars = 5;
        int numberOfBuffalos = 2;
        int numberOfDucks = 10;
        int numberOfCaterpillars = 20;
        int numberOfPlants = 100;

        // Заполнить сетку хищниками
        placeAnimals(numberOfWolves, Wolf::new);
        placeAnimals(numberOfPythons, Python::new);
        placeAnimals(numberOfFoxes, Fox::new);
        placeAnimals(numberOfBears, Bear::new);
        placeAnimals(numberOfEagles, Eagle::new);

        // Заполнить сетку травоядными
        placeAnimals(numberOfHorses, Horse::new);
        placeAnimals(numberOfDeers, Deer::new);
        placeAnimals(numberOfRabbits, Rabbit::new);
        placeAnimals(numberOfMice, Mouse::new);
        placeAnimals(numberOfGoats, Goat::new);
        placeAnimals(numberOfSheep, Sheep::new);
        placeAnimals(numberOfBoars, Boar::new);
        placeAnimals(numberOfBuffalos, Buffalo::new);
        placeAnimals(numberOfDucks, Duck::new);
        placeAnimals(numberOfCaterpillars, Caterpillar::new);
        // Заполнить сетку растениями
        placePlants(numberOfPlants, Plant::new);


    }

     //Методы placePlants и placeAnimals: Добавлены методы для размещения растений и животных в сетке.
    private void placeAnimals(int numberOfAnimals, Supplier<Animal> animalSupplier) {
        for (int i = 0; i < numberOfAnimals; i++) {
            int x = ThreadLocalRandom.current().nextInt(gridWidth);
            int y = ThreadLocalRandom.current().nextInt(gridHeight);
            Cell cell = grid[x][y];
            if (cell.getAnimals().size() < cell.getMaxAnimals()) {
                Animal animal = animalSupplier.get();
                cell.addAnimal(animal);
                animals.add(animal);
            }
        }
    }

    private void placePlants(int numberOfPlants, Supplier<Plant> plantSupplier) {
        for (int i = 0; i < numberOfPlants; i++) {
            int x = ThreadLocalRandom.current().nextInt(gridWidth);
            int y = ThreadLocalRandom.current().nextInt(gridHeight);
            Cell cell = grid[x][y];
            Plant plant = plantSupplier.get();
            cell.addPlant(plant);
            plants.add(plant);
        }
    }


    public void startSimulation(int iterations) {
        for (int i = 0; i < iterations; i++) {
            System.out.println("Iteration " + (i + 1));

            // Обработать действия для всех животных
            for (Animal animal : animals) {
                for (int j = 0; j < stepsPerIteration; j++) {
                    animal.move();
                    animal.eat();
                    animal.reproduce();
                }
            }

            // Обновить состояние клеток, если это необходимо

            // Показать состояние сетки (опционально)
            printGridState();

            // Добавить логику для остановки симуляции, если необходимо
        }
    }

    private void printGridState() {
        // Показать состояние сетки
        for (int y = 0; y < gridHeight; y++) {
            for (int x = 0; x < gridWidth; x++) {
                Cell cell = grid[x][y];
                System.out.print(cell.toString() + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.startSimulation(100); // Запустить симуляцию на 100 итераций
    }
}







