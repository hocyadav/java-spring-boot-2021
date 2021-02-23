package io.hari.att.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.*;

@Getter
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "myconfig")
public class MyConfig {
    String fieldNameSimple;// 1. field name same as yml, 2 value as String type

    Map<String, String> fieldName = new HashMap<>();//1. field name same as yml, 2 key String, 3 value String

    Map<String, ClassA> fieldNameObject = new HashMap<>();//1 field name, 2 key as string

    //stores : simple obj, list obj, map obj
    Map<String, ClassB> fieldNameAdObject = new HashMap<>();


    @Getter
    @Setter
    @ToString
    public static class ClassA {//3 value as obj
        String stringKey;
        List<String> listKey = new ArrayList<>();
    }

    @Getter
    @Setter
    @ToString
    public static class ClassB {
        //simple string
        String name;

        //list
//        List<String> places = new ArrayList<>();
        Set<String> places = new HashSet<>();

        //map
        Map<String, String> keyValues = new HashMap<>();
    }
}
