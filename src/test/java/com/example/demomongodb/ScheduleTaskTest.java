package com.example.demomongodb;

import org.apache.logging.log4j.core.appender.rolling.action.IfAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ScheduledExecutorTask;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;



/**
 * @author: Michael
 * @date: 7/1/2022 6:23 PM
 */
public class ScheduleTaskTest {

    private final static Logger logger = LoggerFactory.getLogger(ScheduleTaskTest.class);

    private ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//    private ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(10, new BasicThreadFactory.Builder().namingPattern("customized-schedule-pool-%d").daemon(true).build());

    private Integer count = 1;

    public Integer getCount() {
        return count;
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return scheduledExecutorService;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    // 2min, 5min, 10min, 20min
    public void triggerNotify(long delay) {
        try {
            logger.info("triggerNotify");
            scheduledExecutorService.schedule(new Task(this), delay, TimeUnit.MINUTES);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public static void main(String[] args) {
        ScheduleTaskTest scheduleTaskTest = new ScheduleTaskTest();
        scheduleTaskTest.triggerNotify(0);
    }

    public static class Task implements Runnable {

        private long[] delayPeriod = new long[]{1, 2, 3, 4};

        private ScheduleTaskTest scheduleTaskTest;

        public Task(ScheduleTaskTest scheduleTaskTest) {
            this.scheduleTaskTest = scheduleTaskTest;
        }

        @Override
        public void run() {
            try {
                boolean b = businessCode();
                Integer count = this.scheduleTaskTest.getCount();
                if (count < 5) {
                    if (!b) {
                        long delay = delayPeriod[count - 1];//1,2,3,4
                        logger.info("delayPeriod:" + delay);
                        this.scheduleTaskTest.setCount(++count); //2,3,4,5
                        this.scheduleTaskTest.triggerNotify(delay);
                    }
                } else {
                    logger.info("after 5 times failure, will send emails.");
                    scheduleTaskTest.getScheduledExecutorService().shutdown();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private boolean businessCode() {
            logger.info("************start businessCode************");
            try {
                logger.info("process the task, please be waiting...");
                TimeUnit.SECONDS.sleep(5);
                int i = 1 / 0;
            } catch (Exception ex) {
                logger.info("error: something comes up in business code.");
                return false;
            } finally {
                logger.info("************end businessCode************");
            }
            return true;
        }
    }
}
