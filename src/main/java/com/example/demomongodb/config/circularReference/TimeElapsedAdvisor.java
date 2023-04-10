package com.example.demomongodb.config.circularReference;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author: Michael
 * @date: 6/2/2022 3:19 PM
 */
@Aspect
@Component
public class TimeElapsedAdvisor {

    private Logger logger=Logger.getLogger(this.getClass());

    @Pointcut(value = "@annotation(com.example.demomongodb.config.circularReference.TimeElapsed)")
    public void op(){}

    @Around(value = "op()&&@annotation(timeElapsed)",argNames = "joinPoint,timeElapsed")
    public Object around(ProceedingJoinPoint joinPoint,TimeElapsed timeElapsed) throws Throwable{
        String targetCLassName=joinPoint.getTarget().getClass().getName();
        String methodName=joinPoint.getSignature().getName();
        String title=targetCLassName+"."+methodName;
        logger.info(">>>["+title+"]");
        long startTime=System.currentTimeMillis();
        try {
            Object result=joinPoint.proceed();
            long endTime=System.currentTimeMillis();
            logger.info(">>>["+title+"]>>>end[time elapsed:"+(endTime-startTime)+"ms]");
            return result;
        } catch (Throwable ex) {
           throw ex;
        }
    }

}
