package com.example.demomongodb.config.listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * @author: Michael
 * @date: 4/26/2022 5:57 PM
 */
public class AuthServletRequestListener implements ServletRequestListener {
    private final Logger logger =Logger.getLogger(getClass());

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        logger.info("requestDestroyed...");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        logger.info("requestInitialized...");
    }
}
