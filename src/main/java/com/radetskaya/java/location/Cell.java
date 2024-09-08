package com.radetskaya.java.location;

import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.plant.Plant;

import java.util.ArrayList;
import java.util.List;

  //Класс Cell: Реализован с методами для управления животными и растениями.
 public class Cell {
    private List<Animal> animals = new ArrayList<>();
    private List<Plant> plants = new ArrayList<>();
    private final int maxAnimals = 10; // Установите максимальное количество животных на клетку

    public List<Animal> getAnimals() {
        return animals;
    }

      public void addAnimal(Animal animal) {
          if (animals.size() < maxAnimals) {
              animals.add(animal);
          }
      }

      // Метод для удаления животного
          public void removeAnimal(Animal animal) {
              animals.remove(animal);
          }

    public int getMaxAnimals() {
        return maxAnimals;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    @Override
    public String toString() {
        return animals.size() + "/" + plants.size();
    }
}