package com.example.demomongodb.controller;

import com.example.demomongodb.controller.viewmodel.Document;
import com.example.demomongodb.domain.TestDocument;
import com.example.demomongodb.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Michael
 * @date: 3/15/2022 9:01 AM
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

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

    @RequestMapping(value = "/getDocument", method = RequestMethod.POST)
    @ResponseBody
    public String getDocument(@RequestBody Document document) {
        return document.toString();
    }
}
