package com.example.demomongodb.repository;

import com.example.demomongodb.domain.PropertyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Michael
 * @date: 9/21/2022 12:57 PM
 */
@Repository
public class PropertyConfigRepository {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MongoTemplate mongoTemplate;

    public void insertProperty(PropertyConfig propertyConfig) {
        PropertyConfig result = mongoTemplate.insert(propertyConfig);
        logger.info("result: " + result);
    }

    public List<PropertyConfig> query(String type, String name) {
        Query query = Query.query(Criteria.where("property_type").is(type).and("property_name").is(name));
        List<PropertyConfig> list = mongoTemplate.find(query, PropertyConfig.class);
        return list;
    }


}
