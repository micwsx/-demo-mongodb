package com.example.demomongodb.config.circularReference;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: Michael
 * @date: 6/1/2022 7:53 PM
 */

class ReferenceATest {

    private static Logger logger = Logger.getLogger(ReferenceATest.class);

    @Test
    void testApp2() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TestApp2.class);
        SelfReferenceB sel = context.getBean(SelfReferenceB.class);
        sel.sayWorld("michael");
    }

    @Test
    void testApp() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TestApp.class);
    }

    // only need ReferenceA,ReferenceB,ReferenceC annotated with Service
    @SpringBootApplication
    @ComponentScan(basePackages = {"com.example.demomongodb.config.circularReference"},
            includeFilters = {@ComponentScan.Filter(type=FilterType.ANNOTATION,classes = Service.class)}
            ,useDefaultFilters = false)
    @EnableAsync
    public static class TestApp {
    }

    // only need SelfReferenceB and TimeElapsedAdvisor and ExecutorConfig
    @SpringBootApplication
    @ComponentScan(basePackages = {"com.example.demomongodb.config.circularReference"},
            includeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {SelfReferenceB.class}),
            @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {TimeElapsedAdvisor.class})
            }
           ,useDefaultFilters = false )
    @Cacheable
    @EnableAsync
    public static class TestApp2 {

    }
}