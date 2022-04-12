package com.example.demomongodb.controller;

import com.example.demomongodb.controller.viewmodel.exception.WeirdException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: Michael
 * @date: 4/12/2022 9:44 PM
 * catch all exceptions here.
 * the exception resolvers have been mapped when start up the springboot.
 * 不管是定义全局异常捕获，还是在Controller单独处理异常，异常类型精确匹配，若没有有找最接近定义父类异常类型。
 */
@ControllerAdvice
public class ExceptionController {

    private final Logger logger= LogManager.getLogger(getClass());

    /**
     * 这种方式Spring启动后就建议的异常处理类映射关系。捕获到所有Controller执行发生的WeirdException异常(前提Controller中没有定义异常处理方法)。
     * @return
     */
    @ExceptionHandler(WeirdException.class)
    public ModelAndView processWiredException(){
        logger.info("ExceptionController-> processWiredException...");
        ModelAndView modelAndView=new ModelAndView();

        return modelAndView;
    }

    /**
     * 这种方式Spring启动后就建议的异常处理类映射关系。捕获到所有Controller执行发生的RuntimeException异常(前提Controller中没有定义异常处理方法)。
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ModelAndView processRuntimeException(){
        logger.info("ExceptionController-> processRuntimeException...");
        ModelAndView modelAndView=new ModelAndView();

        return modelAndView;
    }


}
