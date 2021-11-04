package com.epam.task2.entity;

import java.util.Objects;

public class Oven extends Appliance {
    private int powerConsumption;
    private double weight;
    private int capacity;
    private double depth;
    private double height;
    private double width;

    public Oven() {}

    public Oven(String title, double price, int powerConsumption, double weight,
                int capacity, double depth, double height, double width) {
        super(title, price);
        this.powerConsumption = powerConsumption;
        this.weight = weight;
        this.capacity = capacity;
        this.depth = depth;
        this.height = height;
        this.width = width;
    }

    public int getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(int powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Oven oven = (Oven) o;
        return powerConsumption == oven.powerConsumption && weight == oven.weight && capacity == oven.capacity && depth == oven.depth && Double.compare(oven.height, height) == 0 && Double.compare(oven.width, width) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), powerConsumption, weight, capacity, depth, height, width);
    }

    @Override
    public String toString() {
        return "Oven  -  " +
                "powerConsumption = " + powerConsumption +
                ", weight = " + weight +
                ", capacity = " + capacity +
                ", depth = " + depth +
                ", height = " + height +
                ", width = " + width;
    }
}
