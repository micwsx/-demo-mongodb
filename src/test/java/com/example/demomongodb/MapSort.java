package com.example.demomongodb;

import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: Michael
 * @date: 7/22/2022 11:26 AM
 */
public class MapSort {

    @Test
    void testMapSort(){
        Map<String, String> map = Stream.of(
                new AbstractMap.SimpleEntry<String, String>("B", "1"),
                new AbstractMap.SimpleEntry<String, String>("C", "1"),
                new AbstractMap.SimpleEntry<String, String>("M", "1"),
                new AbstractMap.SimpleEntry<String, String>("D", "1"),
                new AbstractMap.SimpleEntry<String, String>("E", "1"),
                new AbstractMap.SimpleEntry<String, String>("A", "1")
        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey()+"-"+entry.getValue());
        }
    }
}
