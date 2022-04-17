package com.example.demomongodb.log;


/**
 * @author: Michael
 * @date: 4/10/2022 5:27 PM
 */
public class Log4jLogger {


    private static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(Log4jLogger.class);

    public static void main(String[] args) {
        logger.debug("hello");
        logger.trace("hello");
        logger.info("hello");
        logger.warn("hello");
        logger.error("hello");
        logger.fatal("hello world");
    }



}
