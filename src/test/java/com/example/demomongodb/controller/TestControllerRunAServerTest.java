package com.example.demomongodb.controller;

import com.example.demomongodb.service.TestService;
import com.example.demomongodb.service.TestServiceTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @author: Michael
 * @date: 3/15/2022 9:02 AM
 */

// run a server - test mvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TestControllerRunAServerTest {

    @Autowired
    private TestService testService;

    @Test
    void testWithMockMvc(@Autowired MockMvc mvc) throws Exception {
        System.out.println(testService.getAll());
        mvc.perform(get("/test/getAll"))
                .andExpect(status().isOk())
              .andDo(print());
    }

    @Test
    void testSay(@Autowired MockMvc mvc) throws Exception {
        System.out.println(testService.getAll());
        mvc.perform(get("/test/say"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"));
    }


}
