package com.example.demomongodb.controller;

import com.example.demomongodb.controller.viewmodel.exception.WeirdException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Michael
 * @date: 3/15/2022 9:01 AM
 */
@RestController("/mock2")
public class MockException2Controller {

//    private final Logger logger= LoggerFactory.getLogger(getClass());
    private final Logger logger= LogManager.getLogger(getClass());

    @RequestMapping(value = "/weird2", method = RequestMethod.GET)
    public String throwWeirdException() {
        logger.info("throwWeirdException2...");
        throw new WeirdException("This ia a weird exception2.");
    }

    @RequestMapping(value = "/runTime2", method = RequestMethod.GET)
    public String throwRuntimeException() {
        logger.info("throwRuntimeException2...");
        throw new RuntimeException("This is a runtime exception2.");
    }

    @ExceptionHandler(WeirdException.class)
    public String handleWeirdException(){
        logger.info("handleWeirdException2...");
        return "Already handle weird exception2.";
    }

}
