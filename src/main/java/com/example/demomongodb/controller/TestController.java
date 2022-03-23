package com.example.demomongodb.controller;

import com.example.demomongodb.domain.TestDocument;
import com.example.demomongodb.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author: Michael
 * @date: 3/15/2022 9:01 AM
 */
@Controller
@RequestMapping("/test")
public class TestController {


    private TestService testService;

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<TestDocument> getAll() {
        List<TestDocument> list = testService.getAll();
        return list;
    }

    @RequestMapping(value = "/say", method = RequestMethod.GET)
    @ResponseBody
    public String say() {
       return "Hello World";
    }

}
