package com.example.demomongodb.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.Map;

/**
 * @author: Michael
 * @date: 4/21/2022 2:37 PM
 */
public class MapDecoder {
    public static Map<String,String> decodeMap(String value){
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            Map<String, String> map = objectMapper.readValue(value, new TypeReference<Map<String, String>>() {
            });
            return map;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_MAP;
    }
}
