package com.example.demomongodb.controller;

import com.example.demomongodb.controller.viewmodel.DocumentMeta;
import com.example.demomongodb.domain.TestDocument;
import com.example.demomongodb.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Michael
 * @date: 3/15/2022 9:01 AM
 */
@RestController("/test")
public class TestController {

    private TestService testService;

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<TestDocument> getAll() {
        List<TestDocument> list = testService.getAll();
        return list;
    }

    @RequestMapping(value = "/say", method = RequestMethod.GET)
    public String say() {
       return "Hello World";
    }

    @RequestMapping(value = "/getDocument", method = RequestMethod.POST)
    public String getDocument(@RequestBody DocumentMeta document) {
        return document.toString();
    }
}
