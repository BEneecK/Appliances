package com.epam.task2.entity.criteria;

public final class SearchCriteria {

    public static final String LAPTOP = "Laptop";
    public static final String OVEN = "Oven";

    public static enum Laptop {
        BATTERY_CAPACITY, OS, MEMORY_ROM, SYSTEM_MEMORY, DISPLAY_INCHES
    }

    public static enum Oven {
        POWER_CONSUMPTION, WEIGHT, CAPACITY, DEPTH, HEIGHT, WIDTH
    }

    private SearchCriteria() {}
}
