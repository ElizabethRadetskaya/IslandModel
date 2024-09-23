package com.radetskaya.java.main;


import com.radetskaya.java.simulation.startMenu.Menu;
import com.radetskaya.java.swing.MenuWindow;
import com.radetskaya.java.swing.SimulationWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MenuWindow menuWindow = new MenuWindow(); // Відображаємо меню
                menuWindow.setVisible(true);
            }
        });
    }
}