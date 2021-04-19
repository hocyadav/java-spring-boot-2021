package io.hari.demo.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;

/**
 * @Author Hariom Yadav
 * @create 19-04-2021
 */
public class QuestionSoltionConverter implements AttributeConverter<QuestionSolution, String> {
    ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(QuestionSolution questionSolution) {
        if (questionSolution == null) return "";
        return objectMapper.writeValueAsString(questionSolution);
    }

    @SneakyThrows
    @Override
    public QuestionSolution convertToEntityAttribute(String s) {
        if (s == null || s.equals("")) return new QuestionSolution();
        return objectMapper.readValue(s, QuestionSolution.class);
    }
}
