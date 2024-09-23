package com.radetskaya.java.swing;

import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.simulation.IslandSimulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParametersWindow extends JFrame {

    private JTextField rowsField;
    private JTextField columnsField;
    private JTextField predatorsField;
    private JTextField herbivoresField;
    private JTextField plantsField;

    private JButton startButton;
    private JButton cancelButton;

    private int numRows;
    private int numColumns;
    private int numPredators;
    private int numHerbivores;
    private int numPlants;

    public ParametersWindow() {
        setTitle("Налаштування симуляції острова");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2));

        // Поля для налаштування параметрів
        JLabel rowsLabel = new JLabel("Кількість рядків острова:");
        rowsField = new JTextField("10");

        JLabel columnsLabel = new JLabel("Кількість стовпців острова:");
        columnsField = new JTextField("10");

        JLabel predatorsLabel = new JLabel("Кількість хижаків:");
        predatorsField = new JTextField("25");

        JLabel herbivoresLabel = new JLabel("Кількість травоїдних:");
        herbivoresField = new JTextField("30");

        JLabel plantsLabel = new JLabel("Кількість рослин:");
        plantsField = new JTextField("10");

        // Кнопки
        startButton = new JButton("Почати симуляцію");
        cancelButton = new JButton("Скасувати");

        // Додаємо елементи на вікно
        add(rowsLabel);
        add(rowsField);
        add(columnsLabel);
        add(columnsField);
        add(predatorsLabel);
        add(predatorsField);
        add(herbivoresLabel);
        add(herbivoresField);
        add(plantsLabel);
        add(plantsField);
        add(startButton);
        add(cancelButton);

        // Обробка кнопки "Почати симуляцію"
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Зчитуємо введені дані
                    numRows = Integer.parseInt(rowsField.getText());
                    numColumns = Integer.parseInt(columnsField.getText());
                    numPredators = Integer.parseInt(predatorsField.getText());
                    numHerbivores = Integer.parseInt(herbivoresField.getText());
                    numPlants = Integer.parseInt(plantsField.getText());

                    // Створюємо модель острова з введеними параметрами
                    IslandField.getInstance().initializeLocations(numRows, numColumns);
                    IslandSimulation.getInstance().createIslandModel(numHerbivores, numPredators, numPlants);

                    // Закриваємо вікно параметрів і починаємо симуляцію
                    dispose();
                    SimulationWindow simulationWindow = new SimulationWindow();
                    simulationWindow.startSimulation();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Помилка: введіть коректні числа!", "Помилка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Закрити вікно без запуску симуляції
                dispose();
            }
        });
    }
}