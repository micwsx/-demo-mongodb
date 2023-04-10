package com.example.demomongodb.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.*;

/**
 * @author: Michael
 * @date: 4/26/2022 9:53 PM
 */
public class IOUtils {

    public static void main(String[] args) {
        try {
            Person person=new Person();
            person.setName("John");
            person.setAge(129);
            byte[] bytes = IOUtils.writeObject(person);
            Person person2 = IOUtils.readObject(bytes, Person.class);
            System.out.println(person2);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static byte[] writeObject(Object object) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)){
            objectOutputStream.writeObject(object);
            return byteArrayOutputStream.toByteArray();
        }
    }

    public static <T> T readObject(byte[] bytes, Class<T> clazz) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        try(ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)){
            return clazz.cast(objectInputStream.readObject());
        }
    }

    public static class Person implements Serializable{

        @JsonIgnoreProperties
        private String name;
        private Integer age;

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
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
