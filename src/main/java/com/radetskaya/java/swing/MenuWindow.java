package com.radetskaya.java.swing;

import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.simulation.IslandSimulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuWindow extends JFrame {

    private JButton changeParamsButton;
    private JButton defaultParamsButton;

    public MenuWindow() {
        setTitle("Меню симуляції");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 1));

        // Кнопки
        changeParamsButton = new JButton("Змінити параметри");
        defaultParamsButton = new JButton("Використати стандартні налаштування");

        // Додаємо кнопки на вікно
        add(changeParamsButton);
        add(defaultParamsButton);

        // Додаємо обробники подій для кнопок
        changeParamsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Відкрити вікно параметрів після натискання кнопки "Змінити параметри"
                openParametersWindow();
            }
        });

        defaultParamsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Запуск симуляції зі стандартними параметрами
                IslandField.getInstance().initializeLocations();
                IslandSimulation.getInstance().createIslandModel();
                openSimulationWindow(); // Запуск симуляції
            }
        });
    }

    // Метод для відкриття вікна параметрів
    private void openParametersWindow() {
        // Закриваємо меню
        dispose();

        // Відкриваємо вікно для введення параметрів
        ParametersWindow parametersWindow = new ParametersWindow();
        parametersWindow.setVisible(true);
    }

    // Метод для відкриття симуляції
    private void openSimulationWindow() {
        dispose();
        SimulationWindow simulationWindow = new SimulationWindow();
        simulationWindow.startSimulation();
    }
}