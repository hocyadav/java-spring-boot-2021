package io.hari.att.convertor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.hari.att.entity.JsonEntity;

import javax.persistence.AttributeConverter;

public class MyJsonConvertor implements AttributeConverter<JsonEntity, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(JsonEntity entity) {
        try {
            return objectMapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JsonEntity convertToEntityAttribute(String s) {
        try {
            return objectMapper.readValue(s, JsonEntity.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
