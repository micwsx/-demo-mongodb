package com.example.demomongodb.config.circularReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author: Michael
 * @date: 6/1/2022 7:38 PM
 */
@Service
public class ReferenceC {

    @Autowired
    private ReferenceB referenceB;

   @Async
    public String sayWorld(String name){
        String msg="world: "+name;
        System.out.println(msg);
        return msg;
    }


}
