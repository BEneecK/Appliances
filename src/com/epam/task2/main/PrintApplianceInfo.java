package com.epam.task2.main;

import com.epam.task2.entity.Appliance;

import java.util.List;

public class PrintApplianceInfo {

    public static void printApplianceList(List<Appliance> applianceList) {
        for(Appliance appliance: applianceList) {
            System.out.println(appliance);
        }
    }
}
