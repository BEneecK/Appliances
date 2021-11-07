package com.epam.task2.service.validation;

import com.epam.task2.dao.ApplianceParser;
import com.epam.task2.entity.*;
import com.epam.task2.entity.criteria.Criteria;
import com.epam.task2.entity.criteria.SearchCriteria;
import java.util.Map;

public class Validator {

    public static boolean applianceValidator(Appliance appliance) {

        String applianceClass = appliance.getClass().getSimpleName();

        switch (applianceClass) {
            case SearchCriteria.LAPTOP: {
                Laptop laptop = (Laptop) appliance;
                if(laptop.getPrice() <= 0 || laptop.getBatteryCapacity() <=0 ||
                        laptop.getMemoryRom() <=0 || laptop.getSystemMemory() <=0 ||
                        laptop.getDisplayInches() <=0) {
                    return false;
                }
            } break;
            case SearchCriteria.OVEN: {
                Oven oven = (Oven) appliance;
                if (oven.getPrice() <= 0 || oven.getDepth() <= 0 || oven.getWidth() <= 0 ||
                        oven.getHeight() <= 0 || oven.getCapacity() < 0 || oven.getPowerConsumption() < 0) {
                    return false;
                }
            } break;
        }
        return true;
    }
}
