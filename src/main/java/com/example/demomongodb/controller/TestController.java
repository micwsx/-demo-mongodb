package com.example.demomongodb.controller;

import com.example.demomongodb.controller.viewmodel.DocumentMeta;
import com.example.demomongodb.controller.viewmodel.exception.WeirdException;
import com.example.demomongodb.domain.TestDocument;
import com.example.demomongodb.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Michael
 * @date: 3/15/2022 9:01 AM
 */
@RestController
public class TestController {

    private TestService testService;

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @RequestMapping(value = "/test/getAll", method = RequestMethod.GET)
    public List<TestDocument> getAll() {
        List<TestDocument> list = testService.getAll();
        return list;
    }

    @RequestMapping(value = "/test/say", method = RequestMethod.GET)
    public String say() {
        System.out.println("Hello World");
       return "Hello World";
    }

    @RequestMapping(value = "/test/getDocument", method = RequestMethod.POST)
    public String getDocument(@RequestBody DocumentMeta document) {
        System.out.println("DocumentMeta:"+document);
        return document.toString();
    }

    @RequestMapping(value = "/test/testWeird", method = RequestMethod.GET)
    public void testException(){
//        throw new WeirdException("test weird exception.");
        throw new IllegalArgumentException("invalid argument.");
    }

}
