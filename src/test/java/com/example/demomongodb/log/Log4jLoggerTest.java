package com.example.demomongodb.log;


import org.junit.jupiter.api.Test;

/**
 * @author: Michael
 * @date: 4/10/2022 5:27 PM
 */
public class Log4jLoggerTest {


    private static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(Log4jLoggerTest.class);

    @Test
    public void testApacheLog4j(){
        logger.debug("hello");
        logger.trace("hello");
        logger.info("hello");
        logger.warn("hello");
        logger.error("hello");
        logger.fatal("hello world");
    }



}
