package com.example.demomongodb.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: Michael
 * @date: 4/21/2022 2:17 PM
 */
@Configuration
public class ValueFromConfig {

    private final Logger logger= Logger.getLogger(getClass());

    @Value("${countries:}")
    private String[] countries;

    @Value("${arrays}")
    private int[] arrays;

    @Value("#{'${shop.list:}'.split(',')}")
    private List<String> shopList;

    @Value("#{'${shop.set:}'.split(',')}")
    private Set<String> shopSet;

    @Value("#{T(com.example.demomongodb.config.MapDecoder).decodeMap('${shop.map:}')}")
    private List<Map> shopMap;


    public void print(){
        ThreadLocal<String> threadLocal=new ThreadLocal<>();
        threadLocal.set("Hello");

        logger.info(arrays);
        logger.info(countries);
        logger.info(shopList);
        logger.info(shopSet);
        logger.info(shopMap);
    }


}
