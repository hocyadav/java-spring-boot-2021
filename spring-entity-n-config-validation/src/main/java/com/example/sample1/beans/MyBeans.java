package com.example.sample1.beans;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import com.example.sample1.entity.Person;
import com.example.sample1.entity.Person2;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author HariomYadav
 * @since 07/02/21
 */
@Component
public class MyBeans {

    //https://stackoverflow.com/questions/44317816/jsonignoreproperties-usage-for-both-known-and-unknown-properties
    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    //https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application
    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

}
