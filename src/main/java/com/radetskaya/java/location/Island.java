package com.radetskaya.java.location;

import com.radetskaya.java.animal.Animal;

import java.util.ArrayList;
import java.util.List;

class Island {
    private final Location[][] grid;

    public Island(int width, int height) {
        grid = new Location[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Location();
            }
        }
    }

    public Location getLocation(int x, int y) {
        return grid[x][y];
    }

    public boolean isValidLocation(int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }

    public void step() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                List<Animal> animalsCopy = new ArrayList<>(grid[i][j].animals);
                for (Animal animal : animalsCopy) {
                    animal.eat(grid[i][j]);
                    animal.move(this, i, j);
                    animal.reproduce(grid[i][j]);
                    animal.die(grid[i][j]);
                }
            }
        }
    }
}
