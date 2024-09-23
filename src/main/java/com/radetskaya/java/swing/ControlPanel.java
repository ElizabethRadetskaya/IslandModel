package com.radetskaya.java.swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {

    private JButton startButton;
    private JButton pauseButton;

    public ControlPanel() {
        startButton = new JButton("Старт");
        pauseButton = new JButton("Пауза");

        add(startButton);
        add(pauseButton);

        // Додаємо обробники подій для кнопок
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Логіка для запуску симуляції
                System.out.println("Симуляція запущена");
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Логіка для паузи симуляції
                System.out.println("Симуляція на паузі");
            }
        });
    }
}