package com.example.demojava.jpa_set_convertor;

import java.util.Arrays;
import java.util.List;

/**
 * @author HariomYadav
 * @since 28/10/20
 */
public class TestHelperConverter {
    public static void main(String[] args) {
        final List<String> list = Arrays.asList("hari", "om", "yadav");
        HelperConverter helperConverter = new HelperConverter();

        final String dbValue = helperConverter.convertToDatabaseColumn(list);
        System.out.println("dbValue = " + dbValue);

        final List<String> list1 = helperConverter.convertToEntityAttribute(dbValue);
        System.out.println("list1 = " + list1);
    }

}
