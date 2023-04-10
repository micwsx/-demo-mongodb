package com.example.demomongodb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author: Michael
 * @date: 5/1/2022 10:47 AM
 * mongo model -> business model
 */
@Document("test")
public class DocumentDTO {

    private ObjectId id;

    private String description;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DocumentDTO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
