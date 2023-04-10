package com.example.demomongodb.config.circularReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Michael
 * @date: 6/1/2022 7:38 PM
 */
@Component
public class SelfReferenceB {

    @Autowired
    private SelfReferenceB selfReferenceB;

    @Async // 抛异常
//    @Cacheable(cacheNames = "test") // 正常
//    @TimeElapsed // 正常
    public String sayWorld(String name) {
        String msg = "world: " + name;
        System.out.println(msg);
        return msg;
    }

}
