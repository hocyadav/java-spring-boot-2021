package io.hari.att.convertor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.hari.att.entity.EntityAttribute;

import javax.persistence.AttributeConverter;

public class MyJsonAttributeConvertor implements AttributeConverter<EntityAttribute, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(EntityAttribute entityAttribute) {
        try {
            return objectMapper.writeValueAsString(entityAttribute);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public EntityAttribute convertToEntityAttribute(String s) {
        try {
            return objectMapper.readValue(s, EntityAttribute.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
