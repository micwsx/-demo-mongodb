package com.example.demomongodb;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * @author: Michael
 * @date: 7/1/2022 6:23 PM
 */
public class ScheduleTaskTestMain {


    @Test
    void testMain(){
        ScheduleTaskTest scheduleTaskTest = new ScheduleTaskTest();
        scheduleTaskTest.triggerNotify(0);
    }


}
