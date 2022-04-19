package com.example.demomongodb.repository;

import com.example.demomongodb.domain.TestDocument;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author: Michael
 * @date: 3/14/2022 10:02 PM
 * https://www.cnblogs.com/qingmuchuanqi48/p/11886618.html
 */
@SpringBootTest()
public class TestRepositoryTest {

    @Autowired
    private TestRepository testRepository;

    private final Logger logger =Logger.getLogger(getClass());

    private final ObjectMapper objectMapper=new ObjectMapper();

    @Test
    public void findAll(){
        List<TestDocument> docs = testRepository.findAll();
        printJsonObject(docs);
    }

    @Test
    public void insert(){
        TestDocument testDocument=new TestDocument("Lucy",20);
        TestDocument doc = testRepository.insert(testDocument);
        printJsonObject(doc);
    }

    @Test
    void insertAll() {
        TestDocument testDocument=new TestDocument("Lily",22);
        List<TestDocument> list= Arrays.asList(testDocument);
        Collection<TestDocument> docs = testRepository.insertAll(list);
        printJsonObject(docs);
    }


    @Test
    void findWithQuery() {
        Query query = Query.query(Criteria.where("name").is("Lucy"));
        List<TestDocument> docs = testRepository.findWithQuery(query);
        printJsonObject(docs);
    }

    @Test
    void findByNameAndReplace() {
        TestDocument testDocument=new TestDocument("Lily",199);
        TestDocument newDoc = testRepository.findByNameAndReplace(testDocument);
        printJsonObject(newDoc);
    }

    @Test
    void findByNameAndModify() {
        TestDocument testDocument=new TestDocument("Lucy",199);
        TestDocument newDoc  = testRepository.findByNameAndModify(testDocument);
        printJsonObject(newDoc);
    }

    @Test
    void deleteByName() {
        long effectRow = testRepository.deleteByName("Lucy");
        logger.info("delete num: "+effectRow);
    }

    @Test
    void findAllAndRemove() {
        Query query = Query.query(Criteria.where("name").is("Lily"));
        List<TestDocument> docs = testRepository.findAllAndRemove(query);
        printJsonObject(docs);
    }

    @Test
    public void urlEncode(){
        try {
            String encode = URLEncoder.encode("; ", "utf-8");
            String decode = URLDecoder.decode("%20", "utf-8");

            System.out.println(encode);
            System.out.println(decode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void printJsonObject(Object object){
        try {
            String jsonContent = objectMapper.writeValueAsString(object);
            logger.info("jsonObject: "+jsonContent);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
