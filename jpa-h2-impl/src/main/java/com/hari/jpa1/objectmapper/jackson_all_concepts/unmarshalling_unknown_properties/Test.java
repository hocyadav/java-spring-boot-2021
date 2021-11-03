package com.hari.jpa1.objectmapper.jackson_all_concepts.unmarshalling_unknown_properties;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class Test {

    @org.junit.jupiter.api.Test
    @SneakyThrows
    public void sameValueAsOurEntity_WORKING() {
        MyDto myDto = MyDto.builder().id(123).name("hariom").build();
        String jsonString = new ObjectMapper().writeValueAsString(myDto);
        System.out.println("jsonString = " + jsonString);
        MyDto myObject = new ObjectMapper().readValue(jsonString, MyDto.class);
        System.out.println("myObject = " + myObject);
    }

    @org.junit.jupiter.api.Test
    @SneakyThrows
    public void lessValueThanOurEntity_WORKING() {
        String jsonString = "{\"name\":\"hariom\"}";
        System.out.println("jsonString = " + jsonString);
        MyDto myObject = new ObjectMapper().readValue(jsonString, MyDto.class);
        System.out.println("myObject = " + myObject);
    }

    @org.junit.jupiter.api.Test
    @SneakyThrows
    public void moreValueThanOurEntity_ERROR() {
        MyDto myDto = MyDto.builder().id(123).name("hariom").build();
//        String jsonString = new ObjectMapper().writeValueAsString(myDto);
        //INTELLIJ : right click -> inject language -> then right click -> edit json fragment and add new field
        String jsonString = "{\"name\":\"hariom\",\"id\":123, \"location\": \"bangalore\"}";//adding one extra field and that field is not present in entity class
        System.out.println("jsonString = " + jsonString);
        MyDto myObject = new ObjectMapper().readValue(jsonString, MyDto.class);
        System.out.println("myObject = " + myObject);//error UnrecognizedPropertyException ["location"]
    }


    @org.junit.jupiter.api.Test
    @SneakyThrows
    public void moreValueThanOurEntity_WORKING() {
        MyDto myDto = MyDto.builder().id(123).name("hariom").build();
//        String jsonString = new ObjectMapper().writeValueAsString(myDto);
        String jsonString = "{\"name\":\"hariom\",\"id\":123, \"location\": \"bangalore\"}";//adding one extra field and that field is not present in entity class
        System.out.println("jsonString = " + jsonString);
        MyDto myObject = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(jsonString, MyDto.class);
        System.out.println("myObject = " + myObject);
    }


}
