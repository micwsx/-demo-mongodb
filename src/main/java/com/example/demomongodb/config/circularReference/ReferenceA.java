package com.example.demomongodb.config.circularReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author: Michael
 * @date: 6/1/2022 7:38 PM
 */
@Service
public class ReferenceA {

    @Autowired
    private ReferenceB referenceB;

    @Cacheable(cacheNames = "test")
    public String setHello(String name){
        String msg="hello "+name;
        System.out.println(msg);
        return msg;
    }

}
