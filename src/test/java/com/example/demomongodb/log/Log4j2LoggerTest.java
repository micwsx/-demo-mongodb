package com.example.demomongodb.log;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

/**
 * @author: Michael
 * @date: 4/10/2022 5:27 PM
 */
public class Log4j2LoggerTest {

    private static final Logger logger= LogManager.getLogger(Log4j2Logger.class);

    @Test
    public void testApacheLog4j2(){
        logger.debug("hello");
        logger.trace("hello");
        logger.info("hello");
        logger.warn("hello");
        logger.error("hello");
        logger.fatal("hello world");
    }
    
}
