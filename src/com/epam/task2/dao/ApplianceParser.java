package com.epam.task2.dao;

import com.epam.task2.entity.*;
import com.epam.task2.entity.criteria.Criteria;
import com.epam.task2.entity.criteria.SearchCriteria;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.*;


public class ApplianceParser {

    public ApplianceParser() {};

    public static ClassLoader classLoader = ApplianceParser.class.getClassLoader();
    public static final File DB_PATH = new File(classLoader.getResource("resources/appliances_db.xml").getFile());

    public final static String TITLE = "TITLE";
    public final static String PRICE = "PRICE";
    public final static String POWER_CONSUMPTION = "POWER_CONSUMPTION";
    public final static String WEIGHT = "WEIGHT";
    public final static String HEIGHT = "HEIGHT";
    public final static String WIDTH = "WIDTH";
    public final static String CAPACITY = "CAPACITY";
    public final static String DEPTH = "DEPTH";
    public final static String BATTERY_CAPACITY = "BATTERY_CAPACITY";
    public final static String OS = "OS";
    public final static String MEMORY_ROM = "MEMORY_ROM";
    public final static String SYSTEM_MEMORY = "SYSTEM_MEMORY";
    public final static String DISPLAY_INCHES = "DISPLAY_INCHES";


    private static List<Appliance> appliances = new ArrayList<>();

    private static Document parseDB() throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(DB_PATH);
    }

    public List findAppliance(Criteria criteria) throws IOException, ParserConfigurationException, SAXException {
        Document document = parseDB();
        NodeList elements = document.getElementsByTagName(criteria.getGroupSearchName());

        Map<String, Object> parametersOfInstance;
        Map<Appliance, Map<String,Object>> applianceInstances = new HashMap<>();

        for (int i = 0; i < elements.getLength(); i++) {
            NamedNodeMap attributes = elements.item(i).getAttributes();

            switch (criteria.getGroupSearchName()) {
                case SearchCriteria.LAPTOP: {
                    parametersOfInstance = new HashMap<>();
                    applianceInstances = new HashMap<>();

                    buildLaptop(parametersOfInstance, applianceInstances, attributes);
                    break;
                }
                case SearchCriteria.OVEN: {
                    parametersOfInstance = new HashMap<>();
                    applianceInstances = new HashMap<>();

                    buildOven(parametersOfInstance, applianceInstances, attributes);
                    break;
                }
            }

            appliances = mapToList(applianceInstances, criteria);
        }
        return appliances;
    }

    private static void buildLaptop(Map<String, Object> parametersOfInstance,
                                    Map<Appliance, Map<String,Object>> applianceInstances, NamedNodeMap attributes) {

        String title = attributes.getNamedItem(TITLE).getNodeValue();
        double price = Double.parseDouble(attributes.getNamedItem(PRICE).getNodeValue());
        double batteryCapacity = Double.parseDouble(attributes.getNamedItem(BATTERY_CAPACITY).getNodeValue());
        String osType = attributes.getNamedItem(OS).getNodeValue();
        int memoryRom = Integer.parseInt(attributes.getNamedItem(MEMORY_ROM).getNodeValue());
        int systemMemory = Integer.parseInt(attributes.getNamedItem(SYSTEM_MEMORY).getNodeValue());
        double displayInches = Double.parseDouble(attributes.getNamedItem(DISPLAY_INCHES).getNodeValue());

        parametersOfInstance.put(TITLE, title);
        parametersOfInstance.put(PRICE, price);
        parametersOfInstance.put(BATTERY_CAPACITY, batteryCapacity);
        parametersOfInstance.put(OS, osType);
        parametersOfInstance.put(MEMORY_ROM,memoryRom);
        parametersOfInstance.put(SYSTEM_MEMORY, systemMemory);
        parametersOfInstance.put(DISPLAY_INCHES, displayInches);

        applianceInstances.put(new Laptop(title, price, batteryCapacity, osType, memoryRom, systemMemory, displayInches), parametersOfInstance);
    }

    private static void buildOven(Map<String, Object> parametersOfInstance,
                                  Map<Appliance, Map<String,Object>> applianceInstances, NamedNodeMap attributes) {


        String title = attributes.getNamedItem(TITLE).getNodeValue();
        double price = Double.parseDouble(attributes.getNamedItem(PRICE).getNodeValue());
        int powerConsumption = Integer.parseInt(attributes.getNamedItem(POWER_CONSUMPTION).getNodeValue());
        double weight = Double.parseDouble(attributes.getNamedItem(WEIGHT).getNodeValue());
        int capacity = Integer.parseInt(attributes.getNamedItem(CAPACITY).getNodeValue());
        double depth = Double.parseDouble(attributes.getNamedItem(DEPTH).getNodeValue());
        double height = Double.parseDouble(attributes.getNamedItem(HEIGHT).getNodeValue());
        double width = Double.parseDouble(attributes.getNamedItem(WIDTH).getNodeValue());

        parametersOfInstance.put(TITLE, title);
        parametersOfInstance.put(PRICE, price);
        parametersOfInstance.put(POWER_CONSUMPTION, powerConsumption);
        parametersOfInstance.put(WEIGHT, weight);
        parametersOfInstance.put(CAPACITY, capacity);
        parametersOfInstance.put(DEPTH, depth);
        parametersOfInstance.put(HEIGHT, height);
        parametersOfInstance.put(WIDTH, width);

        applianceInstances.put(new Oven(title, price, powerConsumption, weight, capacity, depth, height, width), parametersOfInstance);
    }

    private static List<Appliance> mapToList(Map<Appliance, Map<String,Object>> applianceInstances, Criteria criteria) {

        for (Map.Entry<Appliance, Map<String,Object>> parameters : applianceInstances.entrySet()) {
            int criteriaAmount = criteria.getCriteria().entrySet().size();
            for (Map.Entry<String, Object> specification : parameters.getValue().entrySet()) {
                for (Map.Entry<String, Object> askQuality : criteria.getCriteria().entrySet()) {
                    if(specification.getKey().equals(askQuality.getKey()) && specification.getValue().equals(askQuality.getValue())) {
                        criteriaAmount--;
                        if (criteriaAmount == 0) {
                            appliances.add(parameters.getKey());

                        }
                    }
                }
            }
        }
        return appliances;
    }

    public static void addAppliance(final String applianceType, Appliance appliance) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        Document document = parseDB();

        NodeList nodes = document.getElementsByTagName(applianceType);
        Element element = document.createElement(applianceType);

        switch (applianceType) {
            case SearchCriteria.LAPTOP: {

                createLaptop(appliance, element);
                break;
            }
            case SearchCriteria.OVEN: {

                createOven(appliance, element);
                break;
            }
        }
    }

    private static void createLaptop(Appliance appliance, Element element) {
        Laptop laptop = (Laptop) appliance;

        element.setAttribute(TITLE, laptop.getTitle());
        element.setAttribute(PRICE, ((Double) laptop.getPrice()).toString());
        element.setAttribute(BATTERY_CAPACITY, ((Double) laptop.getBatteryCapacity()).toString());
        element.setAttribute(OS, laptop.getOSType());
        element.setAttribute(MEMORY_ROM, ((Integer) laptop.getMemoryRom()).toString());
        element.setAttribute(SYSTEM_MEMORY, ((Integer) laptop.getSystemMemory()).toString());
        element.setAttribute(DISPLAY_INCHES, ((Double) laptop.getDisplayInches()).toString());
    }

    private static void createOven(Appliance appliance, Element element) {
        Oven oven = (Oven) appliance;

        element.setAttribute(TITLE, oven.getTitle());
        element.setAttribute(PRICE, ((Double) oven.getPrice()).toString());
        element.setAttribute(POWER_CONSUMPTION, ((Integer) oven.getPowerConsumption()).toString());
        element.setAttribute(WEIGHT, ((Double) oven.getWeight()).toString());
        element.setAttribute(CAPACITY, ((Integer) oven.getCapacity()).toString());
        element.setAttribute(DEPTH, ((Double) oven.getDepth()).toString());
        element.setAttribute(HEIGHT, ((Double) oven.getHeight()).toString());
        element.setAttribute(WIDTH, ((Double) oven.getWidth()).toString());
    }
}