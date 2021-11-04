package com.epam.task2.entity;

import java.util.Objects;

public abstract class Appliance {
    private String title;
    private double price;

    public Appliance() {}

    public Appliance(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appliance appliance = (Appliance) o;
        return Double.compare(appliance.price, price) == 0 && Objects.equals(title, appliance.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price);
    }

    @Override
    public String toString() {
        return "Appliance{" +
                "title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
