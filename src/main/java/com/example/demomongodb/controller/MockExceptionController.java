package com.example.demomongodb.controller;

import com.example.demomongodb.controller.viewmodel.DocumentMeta;
import com.example.demomongodb.controller.viewmodel.exception.WeirdException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Michael
 * @date: 3/15/2022 9:01 AM
 */
@RestController("/mock")
public class MockExceptionController {

//    private final Logger logger= LoggerFactory.getLogger(getClass());
    private final Logger logger= LogManager.getLogger(getClass());

    @RequestMapping(value = "/weird", method = RequestMethod.GET)
    public String throwWeirdException() {
        logger.info("throwWeirdException...");
        throw new WeirdException("This ia a weird exception.");
    }

    @RequestMapping(value = "/runTime", method = RequestMethod.GET)
    public String throwRuntimeException() {
        logger.info("throwRuntimeException...");
        throw new RuntimeException("This is a runtime exception.");
    }

    /**
     * 这种方式是在请求发生异常时,Spring分析Controller中是否有处理此类型的异常。
     * 但这个只能捕获到当前Controller的WeirdException异常，无法捕获其它Controller的WeirdException异常
     * @return
     */
    @ExceptionHandler(WeirdException.class)
    public String handleWeirdException(){
        logger.info("handleWeirdException...");
        return "Already handle weird exception.";
    }

    /**
     * 这种方式是在请求发生异常时,Spring分析Controller中是否有处理此类型的异常。
     * 但这个只能捕获到当前Controller的RuntimeException异常，无法捕获其它Controller的RuntimeException异常
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(){
        logger.info("handleRuntimeException...");
        return "Already handle runtime exception.";
    }

}
