package com.radetskaya.java.swing;

import com.radetskaya.java.field.IslandField;
import com.radetskaya.java.simulation.IslandSimulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimulationWindow extends JFrame {

    private IslandPanel islandPanel;        // Панель для відображення острова
    private StatisticsPanel statisticsPanel; // Панель для відображення статистики
    private ControlPanel controlPanel;      // Панель для керування симуляцією
    private ScheduledExecutorService executorService; // Для керування симуляцією

    private boolean isRunning = false; // Прапор для контролю стану симуляції

    // Конструктор вікна симуляції
    public SimulationWindow() {
        setTitle("Симуляція острова");
        setSize(1000, 600);  // Встановлення розміру вікна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Центрування вікна на екрані

        // Створення панелей
        islandPanel = new IslandPanel();  // Панель для острова
        statisticsPanel = new StatisticsPanel();  // Панель для статистики
        controlPanel = new ControlPanel(this);  // Панель для керування симуляцією

        // Розміщення панелей у вікні
        setLayout(new BorderLayout());
        add(islandPanel, BorderLayout.CENTER);  // Відображення острова в центрі
        add(statisticsPanel, BorderLayout.EAST);  // Статистика праворуч
        add(controlPanel, BorderLayout.SOUTH);  // Панель керування внизу

        setVisible(true);  // Показ вікна
    }

    // Метод для запуску симуляції
    public void startSimulation() {
        if (isRunning) return; // Перевірка, щоб уникнути подвійного запуску

        isRunning = true;  // Позначаємо, що симуляція запущена
        executorService = Executors.newScheduledThreadPool(1);

        // Запускаємо симуляцію з періодом в 1 секунду
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                // Оновлюємо стан острова (можна замінити на вашу симуляційну логіку)
                IslandSimulation.getInstance().simulateStep();

                // Оновлення візуалізації острова та статистики
                islandPanel.updateIsland();  // Оновлюємо відображення острова
                updateStatistics();  // Оновлюємо панель статистики
            }
        }, 0, 1, TimeUnit.SECONDS);  // Інтервал у 1 секунду між кроками симуляції
    }

    // Метод для зупинки симуляції
    public void stopSimulation() {
        if (executorService != null) {
            executorService.shutdown();
        }
        isRunning = false;
    }

    // Метод для оновлення статистики
    public void updateStatistics() {
        // Отримуємо дані для відображення (замінити на реальні дані)
        int animals = IslandField.getInstance().getAllAnimals().size();
        int plants = IslandField.getInstance().getAllPlants().size();
        int deaths = IslandSimulation.getInstance().getDeathsCount();  // Метод для підрахунку смертей

        // Оновлюємо статистичну панель
        statisticsPanel.updateStatistics(animals, plants, deaths);
    }

    // Панель для керування симуляцією
    private class ControlPanel extends JPanel {
        private JButton startButton;
        private JButton stopButton;

        public ControlPanel(SimulationWindow window) {
            setLayout(new FlowLayout());

            startButton = new JButton("Старт");
            stopButton = new JButton("Пауза");

            // Обробник натискання кнопки "Старт"
            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    window.startSimulation();
                }
            });

            // Обробник натискання кнопки "Пауза"
            stopButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    window.stopSimulation();
                }
            });

            // Додаємо кнопки на панель керування
            add(startButton);
            add(stopButton);
        }
    }
}