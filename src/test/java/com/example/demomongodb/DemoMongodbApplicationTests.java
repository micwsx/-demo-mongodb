package com.example.demomongodb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

class DemoMongodbApplicationTests {

    @Test
    void contextLoads() {

        System.out.println(System.getProperty("java.io.tmpdir"));
    }

    @Test
    void testMapNull(){
        try {
            Map<String,String> map=new HashMap<>();
            map.put(null,"1");
            map.put("name",null);

            String s = map.get(null);
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testResource(){
        try {
            File file = ResourceUtils.getFile("");
            System.out.println(file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
