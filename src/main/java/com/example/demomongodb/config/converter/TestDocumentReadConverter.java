package com.example.demomongodb.config.converter;

import com.example.demomongodb.domain.DocumentDTO;
import com.mongodb.DBObject;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

/**
 * @author: Michael
 * @date: 5/1/2022 10:46 AM
 */
public class TestDocumentReadConverter implements Converter<Document, DocumentDTO> {

    @Override
    public DocumentDTO convert(Document source) {
        ObjectId id = source.getObjectId("_id");
        String description = source.getString("name") +","+ source.getInteger("age");
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setId(id);
        documentDTO.setDescription(description);
        return documentDTO;
    }
}
