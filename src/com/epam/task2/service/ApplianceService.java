package com.epam.task2.service;

import com.epam.task2.entity.Appliance;
import com.epam.task2.entity.criteria.Criteria;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public interface ApplianceService {

    List<Appliance> find(Criteria criteria) throws IOException, ParserConfigurationException, SAXException;

    void add(String applianceType, Appliance appliance) throws ParserConfigurationException, IOException, TransformerException, SAXException;
}
