package com.example.demomongodb.repository;

import com.example.demomongodb.domain.DocumentDTO;
import com.example.demomongodb.domain.TestDocument;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author: Michael
 * @date: 3/14/2022 9:57 PM
 * https://my.oschina.net/u/3452433/blog/2999501
 */
@Component
public class TestRepository {

    @Autowired
    private MongoConverter mongoConverter;

    private final Logger logger = LoggerFactory.getLogger(getClass());
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

    public TestDocument findById(TestDocument testDocument){
        Query query=Query.query(Criteria.where("_id").is(testDocument.getId()));
        TestDocument newTestDoc = mongoTemplate.findOne(query,TestDocument.class);
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


    public TestDocument findAndModifyPartial(TestDocument doc){
        logger.info("doc: "+doc);
        Query query=Query.query(Criteria.where("_id").is(doc.getId()));
        Update update=Update.fromDocument(new Document("$set",doc));
        TestDocument newDoc = mongoTemplate.findAndModify(query, update
                , FindAndModifyOptions.options().returnNew(true)
                , TestDocument.class);
        return newDoc;
    }


    public TestDocument updatePartial(TestDocument doc){
        Query query=Query.query(Criteria.where("_id").is(doc.getId()));
        Document docBson = convertObjectToJson(doc);// 对象不用转换成Document也能直接使用
        logger.info("docBson： "+docBson);
        Update update=Update.fromDocument(new Document("$set",docBson));
        TestDocument newDoc = mongoTemplate.update(TestDocument.class)
                .matching(query)
                .apply(update)
                .withOptions(FindAndModifyOptions.options().returnNew(true))
                .findAndModifyValue();
        return newDoc;
    }

    public Document convertObjectToJson(TestDocument doc){
//        Update update=Update.fromDocument(new Document("$set",doc));
//        Document updateObject = update.getUpdateObject();
//        String updateObjectJson = updateObject.toJson();
//        logger.info("updateObjectJson： "+updateObjectJson);

        Document document=new Document();
        mongoTemplate.getConverter().write(doc,document);
        String documentJson = document.toJson();
        logger.info("documentJson： "+documentJson);
        return document;

    }

    public DocumentDTO getDTO(String name){
        Query query=Query.query(Criteria.where("name").is(name));
        DocumentDTO documentDTO = mongoTemplate.findOne(query, DocumentDTO.class,"test");
        return documentDTO;
    }


    public DocumentDTO saveDTO(DocumentDTO documentDTO){
        Collection<DocumentDTO> documentDTOS = mongoTemplate.insertAll(Arrays.asList(documentDTO));
        return documentDTOS.toArray(new DocumentDTO[]{})[0];
//        DocumentDTO result = mongoTemplate.insert(documentDTO,"test");
//        return result;
    }






}
