package com.radetskaya.java.location;


class Island {
    Cell[][] grid;

    public Island(int width, int height) {
        grid = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    void simulate() {
        // Основной цикл симуляции поведения животных
    }
}