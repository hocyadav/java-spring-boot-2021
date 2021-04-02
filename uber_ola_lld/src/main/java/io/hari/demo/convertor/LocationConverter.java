package io.hari.demo.convertor;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.hari.demo.entity.Location;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;

/**
 * @Author Hariom Yadav
 * @create 02-04-2021
 */
public class LocationConverter implements AttributeConverter<Location, String> {
    ObjectMapper objectMapper = new ObjectMapper();
    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(Location location) {
        if (location == null) return "";
        return objectMapper.writeValueAsString(location);
    }

    @SneakyThrows
    @Override
    public Location convertToEntityAttribute(String s) {
        if (s == "" || s == null) return null;
        return objectMapper.readValue(s, Location.class);
    }
}
