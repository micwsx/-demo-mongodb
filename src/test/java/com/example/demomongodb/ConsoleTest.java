package com.example.demomongodb;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import org.junit.jupiter.api.Test;

/**
 * @author: Michael
 * @date: 3/21/2022 11:43 AM
 */

public class ConsoleTest {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class  MyBean{
//        @JsonProperty
        private String name;
        private Integer age;

//        @JsonIgnore
        public String getName() {
            return name;
        }

        @JsonIgnore
        public void setName(String name) {
            this.name = name;
        }

//        @JsonIgnore
//        public String getNameewerwer() {
//            return name;
//        }


        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "MyBean{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public enum TestEnum{
        Jack,Michael,Lison;
    }

    @Test
    public void testIgnoreProperty(){
//        TestEnum lison = TestEnum.Lison;
//        System.out.println(lison.name());


        try {
            ObjectMapper objectMapper = new ObjectMapper();
            MyBean myBean =new MyBean();
            myBean.setName("Michael");
            myBean.setAge(30);
            String objJson = objectMapper.writeValueAsString(myBean);
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            System.out.println("myBean: "+objJson); //序列化时忽略了字段

            String jsonString="{\"name\":\"Michael\",\"age\":30,\"length\":11}";
            MyBean myBean1 = objectMapper.readValue(jsonString, MyBean.class);
            System.out.println(myBean1);// 反序列化时忽略了字段


            System.out.println("testIgnoreProperty");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
