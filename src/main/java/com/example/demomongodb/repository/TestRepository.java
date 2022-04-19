package com.example.demomongodb.repository;

import com.example.demomongodb.domain.TestDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Collection;
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

    public List<TestDocument> findWithQuery(Query query){
        List<TestDocument> list = mongoTemplate.find(query, TestDocument.class);
        return list;
    }

    public TestDocument insert(TestDocument testDocument){
        TestDocument insertedOne = mongoTemplate.insert(testDocument);
        return insertedOne;
    }

    public Collection<TestDocument> insertAll(List<TestDocument> testDocument){
        Collection<TestDocument>  list = mongoTemplate.insertAll(testDocument);
        return list;
    }

    public TestDocument findByNameAndReplace(TestDocument testDocument){
        Query query=Query.query(Criteria.where("name").is(testDocument.getName()));
        TestDocument newTestDoc = mongoTemplate.findAndReplace(query, testDocument,
                FindAndReplaceOptions.options().returnNew());
        return newTestDoc;
    }

    public TestDocument findByNameAndModify(TestDocument testDocument){
        Query query=Query.query(Criteria.where("name").is(testDocument.getName()));
        Update update=Update.update("age",testDocument.getAge());
        TestDocument newTestDoc = mongoTemplate.findAndModify(query, update,
                FindAndModifyOptions.options().returnNew(true),TestDocument.class);
        return newTestDoc;
    }


    public long deleteByName(String name){
        Query query=Query.query(Criteria.where("name").is(name));
        long effectRow= delete(query, TestDocument.class);
        return effectRow;
    }

    public long delete(Query query,Class<?> clazz){
        long effectRow= mongoTemplate.remove(query, clazz).getDeletedCount();
        return effectRow;
    }

    public List<TestDocument> findAllAndRemove(Query query){
        List<TestDocument> list = findAllAndRemove(query,TestDocument.class);
        return list;
    }

    public <T> List<T> findAllAndRemove(Query query,Class<T> clazz){
        List<T> list = mongoTemplate.findAllAndRemove(query, clazz);
        return list;
    }





}
