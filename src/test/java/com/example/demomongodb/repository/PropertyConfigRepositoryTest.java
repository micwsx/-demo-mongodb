package com.example.demomongodb.repository;

import com.example.demomongodb.domain.PropertyConfig;
import com.example.demomongodb.domain.TimeSpan;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: Michael
 * @date: 9/21/2022 10:20 PM
 */
@SpringBootTest
class PropertyConfigRepositoryTest {

    private static final Logger logger= LoggerFactory.getLogger(PropertyConfigRepositoryTest.class);
    @Autowired
    private PropertyConfigRepository repository;

    @Test
    void insertProperty() {
        PropertyConfig propertyConfig=new PropertyConfig();
        propertyConfig.setPropertyType("GREEN_ZONE");
        propertyConfig.setPropertyName("Credit");
        propertyConfig.setPropertyValue(new TimeSpan(new Date(),new Date()));
        repository.insertProperty(propertyConfig);
    }

    @Test
    void readProperty() {
        List<PropertyConfig> query = repository.query("GREEN_ZONE", "Credit");
        if (query.size()>0){
            Object propertyValue = query.get(0).getPropertyValue();
            logger.info("propertyValue: "+propertyValue);
        }
    }
}