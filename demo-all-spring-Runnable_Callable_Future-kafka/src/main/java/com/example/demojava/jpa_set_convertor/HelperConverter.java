package com.example.demojava.jpa_set_convertor;

import java.util.Arrays;
import java.util.List;
import javax.persistence.AttributeConverter;

/**
 * @author HariomYadav
 * @since 28/10/20
 */
public class HelperConverter implements AttributeConverter<List<String>, String> {
    // add maven https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa/2.3.4.RELEASE
    // stack over flow - https://stackoverflow.com/questions/287201/how-to-persist-a-property-of-type-liststring-in-jpa

    final String delimiter = ", ";

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        final String join = String.join(delimiter, attribute);
        return join;
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        final String[] split = dbData.split(delimiter);
        final List<String> strings = Arrays.asList(split);
        return strings;
    }
}
