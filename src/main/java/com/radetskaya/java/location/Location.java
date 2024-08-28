package com.radetskaya.java.location;

import com.radetskaya.java.animal.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Location {
    int plants;  // Кількість рослин у локації
    List<Animal> animals;  // Список тварин у локації

    public Location() {
        this.plants = new Random().nextInt(11);  // Випадкове число рослин від 0 до 10
        this.animals = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }
}