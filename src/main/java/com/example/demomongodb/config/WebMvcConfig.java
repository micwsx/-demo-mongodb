package com.example.demomongodb.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author: Michael
 * @date: 3/28/2022 3:53 PM
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public WebMvcConfig() {
        System.out.println("WebMvcConfig");
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        Optional<HttpMessageConverter<?>> optionalHttpMessageConverter = converters.stream()
                .filter(c -> c.getClass() == MappingJackson2HttpMessageConverter.class)
                .findFirst();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter=new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
        ObjectMapper objectMapper=new ObjectMapper();
        String datePattern="yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
        objectMapper.setTimeZone(TimeZone.getTimeZone(ZoneOffset.UTC));
        objectMapper.setDateFormat(new SimpleDateFormat(datePattern));
        MappingJackson2HttpMessageConverter httpMessageConverter = (MappingJackson2HttpMessageConverter)optionalHttpMessageConverter.orElse(mappingJackson2HttpMessageConverter);
        httpMessageConverter.setObjectMapper(objectMapper);
    }
}
