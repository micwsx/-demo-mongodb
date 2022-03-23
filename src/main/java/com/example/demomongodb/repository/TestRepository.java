package com.example.demomongodb.repository;

import com.example.demomongodb.domain.TestDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Michael
 * @date: 3/14/2022 9:57 PM
 * https://my.oschina.net/u/3452433/blog/2999501
 */
@Component
public class TestRepository {

    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<TestDocument> findAll(){
        List<TestDocument> list = mongoTemplate.findAll(TestDocument.class);
        return list;
    }

}
