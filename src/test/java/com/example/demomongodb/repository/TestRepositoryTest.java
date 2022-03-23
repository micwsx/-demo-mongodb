package com.example.demomongodb.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author: Michael
 * @date: 3/14/2022 10:02 PM
 * https://www.cnblogs.com/qingmuchuanqi48/p/11886618.html
 */
@SpringBootTest()
public class TestRepositoryTest {

    @Autowired
    private TestRepository testRepository;

    @Test
    public void findAll(){
        System.out.println(testRepository.findAll().size());
    }

    @Test
    public void urlEncode(){
        try {
            String encode = URLEncoder.encode("; ", "utf-8");
            String decode = URLDecoder.decode("%20", "utf-8");

            System.out.println(encode);
            System.out.println(decode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
