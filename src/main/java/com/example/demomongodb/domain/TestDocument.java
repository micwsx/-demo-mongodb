package com.example.demomongodb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author: Michael
 * @date: 3/14/2022 9:58 PM
 */
@Document("test")
public class TestDocument {

    @Id
    @JsonIgnore
    private ObjectId id;

    private String name;

    private Integer age;

    public TestDocument() {
    }

    public TestDocument(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
