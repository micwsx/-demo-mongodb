package com.example.demomongodb.controller;

import com.example.demomongodb.repository.TestRepository;
import com.example.demomongodb.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;


/**
 * @author: Michael
 * @date: 3/15/2022 9:02 AM
 * run without web server.
 */
@WebMvcTest(TestController.class)
public class TestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TestRepository testRepository;

    @MockBean
    private TestService testService;

    @Test
    void testExample() throws Exception {
        System.out.println("testRepository: " + testRepository);
        this.mvc.perform(get("/test/say").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect((content().string("Hello World")));
    }

    @Test
    void getDocument() throws Exception {
//        ObjectMapper objectMapper=new ObjectMapper();
//        Document document=new Document("test.txt","2021-09 09 12:33:09");
//        Date utcDate=DateUtil.getUTCOrGMTDateFromText("2022-09-09T04:10:02.013+00:00");
//        String formatDate = DateUtil.formatDate(utcDate, "yyyy-MM-dd hh:mm:ss");
//        System.out.println(formatDate);
//        Document document=new Document("test.txt", utcDate);
//        String jsonContent = objectMapper.writeValueAsString(document);
//       String jsonContent="{\"documentName\":\"test.txt\",\"createdDate\":\"2022-09-09 12:10:02\"}";
       String jsonContent="{\"documentName\":\"test.txt\",\"createdDate\":\"2022-09-09T04:10:02.013+00:00\"}";
        System.out.println("jsonContent: "+jsonContent);
        this.mvc.perform(post("/getDocument")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent)
                .accept(MediaType.TEXT_PLAIN)
        ).andDo(print());
    }
}
