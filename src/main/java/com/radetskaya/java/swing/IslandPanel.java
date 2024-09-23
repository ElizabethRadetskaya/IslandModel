package com.radetskaya.java.swing;

import com.radetskaya.java.animal.Animal;
import com.radetskaya.java.animal.predator.Predator;
import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.field.Location;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class IslandPanel extends JPanel {

    private int numRows;  // Кількість рядків на острові
    private int numCols;  // Кількість стовпців на острові
    private int cellSize = 50; // Розмір кожної клітинки

    // Конструктор класу. Використовує розмір острова з симуляції
    public IslandPanel() {
        this.numRows = IslandField.getInstance().getNumRows();
        this.numCols = IslandField.getInstance().getNumColumns();
        setPreferredSize(new Dimension(numCols * cellSize, numRows * cellSize)); // Встановлюємо розмір панелі
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Отримуємо стан кожної локації з IslandField
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Location location = IslandField.getInstance().getLocation(row, col);

                // Малюємо клітинку
                g.setColor(Color.BLACK); // Чорні межі клітинок
                g.drawRect(col * cellSize, row * cellSize, cellSize, cellSize);

                // Відображаємо рослини, якщо є
                if (!location.getPlants().isEmpty()) {
                    g.setColor(Color.GREEN); // Зелений для рослин
                    g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
                }

                // Відображаємо тварин, якщо є
                List<Animal> animals = location.getAnimals();
                if (!animals.isEmpty()) {
                    if (animals.stream().anyMatch(a -> a instanceof Predator)) {
                        g.setColor(Color.RED); // Червоний для хижаків
                    } else {
                        g.setColor(Color.BLUE); // Синій для травоїдних
                    }
                    g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
                }
            }
        }
    }

    // Оновлюємо панель після кожної ітерації симуляції
    public void updateIsland() {
        repaint(); // Перемальовуємо панель для відображення поточного стану
    }
}