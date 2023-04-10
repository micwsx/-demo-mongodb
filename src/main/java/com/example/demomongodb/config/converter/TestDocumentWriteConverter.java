package com.example.demomongodb.config.converter;

import com.example.demomongodb.domain.DocumentDTO;
import com.example.demomongodb.domain.TestDocument;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

/**
 * @author: Michael
 * @date: 5/1/2022 10:46 AM
 */
public class TestDocumentWriteConverter implements Converter<DocumentDTO,Document> {

    @Override
    public Document convert(DocumentDTO source) {
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            String[] split = source.getDescription().split(",");
            String name=split[0];
            Integer age=Integer.parseInt(split[1]);
            TestDocument testDocument=new TestDocument(name,age);
            String jsonContent = objectMapper.writeValueAsString(testDocument);
            System.out.println(jsonContent);
            return Document.parse(jsonContent);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
