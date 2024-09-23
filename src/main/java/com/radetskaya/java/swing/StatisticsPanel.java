package com.radetskaya.java.swing;

import javax.swing.*;
import java.awt.*;

public class StatisticsPanel extends JPanel {
    private JLabel animalsLabel;
    private JLabel plantsLabel;
    private JLabel deathsLabel;

    public StatisticsPanel() {
        setLayout(new GridLayout(3, 1));

        animalsLabel = new JLabel("Тварин: 0");
        plantsLabel = new JLabel("Рослин: 0");
        deathsLabel = new JLabel("Смертей: 0");

        add(animalsLabel);
        add(plantsLabel);
        add(deathsLabel);
    }

    public void updateStatistics(int animals, int plants, int deaths) {
        animalsLabel.setText("Тварин: " + animals);
        plantsLabel.setText("Рослин: " + plants);
        deathsLabel.setText("Смертей: " + deaths);
    }
}