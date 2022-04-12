package com.example.demomongodb.controller;

import com.example.demomongodb.controller.viewmodel.DocumentMeta;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: Michael
 * @date: 3/29/2022 5:02 PM
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DocumentControllerTest {

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private MockMvc mockMvc;

    @Test
    void uploadDocumentTest() {
        try {
            String jsonList = createDocumentList();
            System.out.println("jsonString: " + jsonList);

            MockMultipartFile file1 = new MockMultipartFile("files","file1.txt",MediaType.TEXT_PLAIN_VALUE,"file1  content".getBytes());
            MockMultipartFile file2 = new MockMultipartFile("files", "file2.txt",MediaType.TEXT_PLAIN_VALUE,"file2  content".getBytes());
            MockMultipartFile docs= new MockMultipartFile("docs","",MediaType.APPLICATION_JSON_VALUE, jsonList.getBytes());
            mockMvc.perform(MockMvcRequestBuilders.multipart("/document/uploadDocuments")
                            .file(file1)
                            .file(file2)
                            .file(docs)
                            .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                            .accept(MediaType.APPLICATION_JSON)
            ).andDo(print());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getDocument1() {
        try {
            String jsonList = createDocumentList();
            MockMultipartFile file1 = new MockMultipartFile("files","file1.txt",MediaType.TEXT_PLAIN_VALUE,"file1  content".getBytes());
            MockMultipartFile file2 = new MockMultipartFile("files","file2  content".getBytes());
            MockMultipartFile docs= new MockMultipartFile("docs","",MediaType.APPLICATION_JSON_VALUE, jsonList.getBytes());
            mockMvc.perform(MockMvcRequestBuilders.multipart("/document/uploadDocuments")
                    .file(file1)
                    .file(file2)
                    .file(docs)
                    .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                    .param("test","testDemoParam")
            .accept(MediaType.APPLICATION_JSON)
            ).andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String createDocumentList() {
        try {
            List<DocumentMeta> list = Arrays.asList(
                    new DocumentMeta("file1.txt", new Date()),
                    new DocumentMeta("file2.txt", new Date())
            );
            String jsonString = objectMapper.writeValueAsString(list);
//            String jsonString = "[{\"documentName\":\"\",\"createdDate\":\"2022-03-29T09:09:56.349+00:00\"},{\"documentName\":\"\",\"createdDate\":\"2022-03-29T09:09:56.349+00:00\"}]";
            return jsonString;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}