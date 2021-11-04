package com.epam.task2.dao.impl;

import com.epam.task2.dao.ApplianceDAO;
import com.epam.task2.dao.ApplianceParser;
import com.epam.task2.entity.Appliance;
import com.epam.task2.entity.criteria.Criteria;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public class ApplianceDAOImpl implements ApplianceDAO {

    @Override
    public List<Appliance> find(Criteria criteria) throws IOException, ParserConfigurationException, SAXException {

        ApplianceParser parser = new ApplianceParser();

        List <Appliance> appliancesWithParameters = parser.findAppliance(criteria);

        return appliancesWithParameters;
    }

    @Override
    public void add(String applianceType, Appliance appliance) throws ParserConfigurationException, IOException, TransformerException, SAXException {

        ApplianceParser.addAppliance(applianceType, appliance);
    }
}