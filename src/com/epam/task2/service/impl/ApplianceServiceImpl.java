package com.epam.task2.service.impl;

import com.epam.task2.dao.ApplianceDAO;
import com.epam.task2.dao.DAOFactory;
import com.epam.task2.entity.Appliance;
import com.epam.task2.entity.criteria.Criteria;
import com.epam.task2.service.ApplianceService;
import com.epam.task2.service.validation.Validator;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public class ApplianceServiceImpl implements ApplianceService {

    @Override
    public List<Appliance> find(Criteria criteria) throws IOException, ParserConfigurationException, SAXException {
        if (!Validator.criteriaValidator(criteria)) {
            return null;
        }

        DAOFactory factory = DAOFactory.getInstance();
        ApplianceDAO applianceDAO = factory.getApplianceDAO();

        return applianceDAO.find(criteria);
    }

    @Override
    public void add (String applianceType, Appliance appliance) throws ParserConfigurationException, IOException, TransformerException, SAXException {

        if (!Validator.applianceValidator(appliance)) {
            return;
        }

        DAOFactory factory = DAOFactory.getInstance();
        ApplianceDAO applianceDAO = factory.getApplianceDAO();

        applianceDAO.add(applianceType, appliance);
    };
}
