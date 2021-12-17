package io.hari.demo.entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;
import java.util.Optional;

public class WorkConverter implements AttributeConverter<Person.WorkDetail, String> {
    private ObjectMapper objectMapper = new ObjectMapper();
    public static final Person.WorkDetail WORK_DETAIL = Person.WorkDetail.builder().build();

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(Person.WorkDetail workDetail) {
        return objectMapper.writeValueAsString(Optional.ofNullable(workDetail).orElse(WORK_DETAIL));
    }

    @SneakyThrows
    @Override
    public Person.WorkDetail convertToEntityAttribute(String s) {
        return objectMapper.readValue(
                Optional.ofNullable(s).orElse(objectMapper.writeValueAsString(WORK_DETAIL)),
                Person.WorkDetail.class);
    }
}
