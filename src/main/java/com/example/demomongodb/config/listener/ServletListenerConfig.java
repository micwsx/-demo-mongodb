package com.example.demomongodb.config.listener;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestListener;

/**
 * @author: Michael
 * @date: 4/26/2022 6:01 PM
 */
@Configuration
public class ServletListenerConfig {

    @Bean
    public ServletListenerRegistrationBean<ServletRequestListener> listenerServletListenerRegistrationBean(){
        ServletListenerRegistrationBean<ServletRequestListener> bean=new ServletListenerRegistrationBean<>();
        bean.setListener(new AuthServletRequestListener());
        return bean;
    }
}
