package io.hari.demo.entity.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.hari.demo.entity.ContestQuestionSolution;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;

/**
 * @Author Hariom Yadav
 * @create 19-04-2021
 */
public class ContestQuestionSolutionConverter implements AttributeConverter<ContestQuestionSolution, String> {
    ObjectMapper objectMapper = new ObjectMapper();
    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(ContestQuestionSolution contestQuestionSolution) {
        if (contestQuestionSolution == null) return "";
        return objectMapper.writeValueAsString(contestQuestionSolution);
    }

    @SneakyThrows
    @Override
    public ContestQuestionSolution convertToEntityAttribute(String s) {
        if (s == null || s.equals("")) return new ContestQuestionSolution();
        return objectMapper.readValue(s, ContestQuestionSolution.class);
    }
}
