package com.example.demomongodb.service;

import com.example.demomongodb.domain.TestDocument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.webservices.client.WebServiceClientTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author: Michael
 * @date: 3/15/2022 10:17 AM
 */
@SpringBootTest
public class TestServiceTest {

    @Autowired
    private TestService testService;

    @Test
    public void getAll(){
        List<TestDocument> list = testService.getAll();
        System.out.println(list);
    }

}
