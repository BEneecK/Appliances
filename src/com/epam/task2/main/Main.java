package com.epam.task2.main;


import com.epam.task2.entity.*;
import com.epam.task2.entity.criteria.Criteria;
import com.epam.task2.entity.criteria.SearchCriteria;
import com.epam.task2.service.ApplianceService;
import com.epam.task2.service.ServiceFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        ServiceFactory factory = ServiceFactory.getInstance();
        ApplianceService service = factory.getApplianceService();

        Criteria criteriaOven = new Criteria(Oven.class.getSimpleName());
        criteriaOven.add(SearchCriteria.Oven.CAPACITY.toString(), 32);
        criteriaOven.add(SearchCriteria.Oven.POWER_CONSUMPTION.toString(), 1500);

        List<Appliance> filteredOvenList;
        filteredOvenList = service.find(criteriaOven);

        PrintApplianceInfo.printApplianceList(filteredOvenList);

        ///////////////////////////////////////////////////////////////////////////////

        Criteria criteriaLaptop = new Criteria(Laptop.class.getSimpleName());
        criteriaLaptop.add(SearchCriteria.Laptop.DISPLAY_INCHES.toString(), 19.0);
        criteriaLaptop.add(SearchCriteria.Laptop.MEMORY_ROM.toString(), 4500);

        List<Appliance> filteredLaptopList;
        filteredLaptopList = service.find(criteriaLaptop);

        PrintApplianceInfo.printApplianceList(filteredLaptopList);
    }
}
