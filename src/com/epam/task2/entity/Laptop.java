package com.epam.task2.entity;

import java.util.Objects;

public class Laptop extends Appliance {
    private double batteryCapacity;
    private String OSType;
    private int memoryRom;
    private int systemMemory;
    private double displayInches;

    public Laptop() {}

    public Laptop(String title, double price, double batteryCapacity, String OSType,
                  int memoryRom, int systemMemory, double displayInches) {
        super(title, price);
        this.batteryCapacity = batteryCapacity;
        this.OSType = OSType;
        this.memoryRom = memoryRom;
        this.systemMemory = systemMemory;
        this.displayInches = displayInches;
    }

    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public String getOSType() {
        return OSType;
    }

    public void setOSType(String OSType) {
        this.OSType = OSType;
    }

    public int getMemoryRom() {
        return memoryRom;
    }

    public void setMemoryRom(int memoryRom) {
        this.memoryRom = memoryRom;
    }

    public int getSystemMemory() {
        return systemMemory;
    }

    public void setSystemMemory(int systemMemory) {
        this.systemMemory = systemMemory;
    }

    public double getDisplayInches() {
        return displayInches;
    }

    public void setDisplayInches(double displayInches) {
        this.displayInches = displayInches;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Laptop laptop = (Laptop) o;
        return Double.compare(laptop.batteryCapacity, batteryCapacity) == 0 && memoryRom == laptop.memoryRom && systemMemory == laptop.systemMemory && Double.compare(laptop.displayInches, displayInches) == 0 && Objects.equals(OSType, laptop.OSType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), batteryCapacity, OSType, memoryRom, systemMemory, displayInches);
    }

    @Override
    public String toString() {
        return "Laptop  -  " +
                "batteryCapacity = " + batteryCapacity +
                ", OSType - '" + OSType + '\'' +
                ", memoryRom = " + memoryRom +
                ", systemMemory = " + systemMemory +
                ", displayInches = " + displayInches;
    }
}
