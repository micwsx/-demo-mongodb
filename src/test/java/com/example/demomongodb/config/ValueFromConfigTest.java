package com.example.demomongodb.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author: Michael
 * @date: 4/21/2022 2:23 PM
 */
@SpringBootTest
class ValueFromConfigTest {

    @Autowired
    private ValueFromConfig valueFromConfig;

    @Test
    void print() {
        String[] arr=new String[]{"1","2"};
        int[] ints = Arrays.stream(arr).mapToInt(Integer::valueOf).toArray();

        valueFromConfig.print();
    }

    @Test
    void testSet(){
        HashSet<String> set1= new HashSet<String>(){{
            add("A");
            add("B");
            add("C");
        }};
        HashSet<String> set2= new HashSet<String>(){{
                    add("B");
                    add("D");
        }};

        set1.removeAll(set2);

        System.out.println(set1);

    }

}