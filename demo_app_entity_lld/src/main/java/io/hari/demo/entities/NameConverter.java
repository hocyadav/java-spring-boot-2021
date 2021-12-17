package io.hari.demo.entities;

import javax.persistence.AttributeConverter;


public class NameConverter implements AttributeConverter<Person.Name, String> {
    private static String seperator = "#";

    @Override
    public String convertToDatabaseColumn(Person.Name name) {
        StringBuilder sb = new StringBuilder();
        return sb.append(name.firstName).append(seperator)
                .append(name.lastName).append(seperator)
                .append(name.alias).toString();
    }

    @Override
    public Person.Name convertToEntityAttribute(String s) {
        String[] split = s.split("#");
        String s1 = split[0];
        String s2 = split[1];
        String s3 = split[2];
        return Person.Name.builder()
                .firstName(s1)
                .lastName(s2)
                .alias(s3)
                .build();
    }
}
