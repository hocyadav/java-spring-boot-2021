package io.hari.demo.entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;
import java.util.Optional;

public class PhotoConverter implements AttributeConverter<Person.PersonPhoto, String> {
    private ObjectMapper objectMapper = new ObjectMapper();
    public final Person.PersonPhoto PERSON_PHOTO = Person.PersonPhoto.builder().build();

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(Person.PersonPhoto personPhoto) {
        return objectMapper.writeValueAsString(
                Optional.ofNullable(personPhoto).orElse(PERSON_PHOTO));
    }

    @SneakyThrows
    @Override
    public Person.PersonPhoto convertToEntityAttribute(String s) {
        return objectMapper.readValue(
                Optional.ofNullable(s).orElse(objectMapper.writeValueAsString(PERSON_PHOTO)),
                Person.PersonPhoto.class);
    }
}
