package io.hari.demo.entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;
import java.util.Optional;

public class PhoneConverter implements AttributeConverter<Person.PhoneNumber, String> {
    ObjectMapper objectMapper = new ObjectMapper();


    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(Person.PhoneNumber phoneNumber) {
        return objectMapper.writeValueAsString(
                Optional.ofNullable(phoneNumber)
                        .orElse(Person.PhoneNumber.builder().build()));
    }

    @SneakyThrows
    @Override
    public Person.PhoneNumber convertToEntityAttribute(String s) {
        if (s == null || s == "") return Person.PhoneNumber.builder().build();
        return objectMapper.readValue(s, Person.PhoneNumber.class);
    }
}
