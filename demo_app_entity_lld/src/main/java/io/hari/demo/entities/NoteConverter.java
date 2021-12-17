package io.hari.demo.entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;

public class NoteConverter implements AttributeConverter<Person.Note, String> {
    ObjectMapper objectMapper = new ObjectMapper();
    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(Person.Note note) {
        if (note == null) return "";
        return objectMapper.writeValueAsString(note);
    }

    @Override
    public Person.Note convertToEntityAttribute(String s) {
        if (s == null || s == "") return Person.Note.builder().build();
        return Person.Note.builder().note(s).build();
    }
}
