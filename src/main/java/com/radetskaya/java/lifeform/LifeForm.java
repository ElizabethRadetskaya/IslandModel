package com.radetskaya.java.lifeform;

public class LifeForm {
    private final double weight; //  Вага тварини/рослини в кг
    private final int maxPopulation; // Максимальна кількість вида твапин/рослтн на 1 клітинкі
    private final String name; // Імʼя тварини/рослини
    private int row;
    private int column;

    /**
     * Конструктор класу LifeForm
     *
     * @param weight        Вага тварини/рослини в кг
     * @param maxPopulation Максимальна кількість вида твапин/рослтн на 1 клітинкі
     * @param name          Імʼя тварини/рослини
     */
    public LifeForm(double weight, int maxPopulation, String name) {
        this.weight = weight;
        this.maxPopulation = maxPopulation;
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }
    public int getMaxPopulation() {
        return maxPopulation;
    }
    public String getName() {
        return name;
    }
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public void setColumn(int column) {
        this.column = column;
    }
}
