package com.example.demomongodb.config.converter;

import com.example.demomongodb.domain.DocumentDTO;
import com.example.demomongodb.domain.PropertyConfig;
import com.example.demomongodb.domain.TestDocument;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

/**
 * @author: Michael
 * @date: 5/1/2022 10:46 AM
 */
public class PropertyValueWriteConverter implements Converter<PropertyConfig,Document> {

    private final static Logger logger= LoggerFactory.getLogger(PropertyValueWriteConverter.class);
    @Override
    public Document convert(PropertyConfig source) {
        ObjectMapper objectMapper=new ObjectMapper();
        try {
//            String type=source.getPropertyType();
//            String name=source.getPropertyName();
//            Object value=source.getPropertyValue();
            String jsonContent = objectMapper.writeValueAsString(source);
            logger.info("PropertyValueWriteConverter->source "+jsonContent);
            return Document.parse(jsonContent);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
