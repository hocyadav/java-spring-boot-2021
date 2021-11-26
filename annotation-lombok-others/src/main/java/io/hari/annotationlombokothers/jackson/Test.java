package io.hari.annotationlombokothers.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class Test {
    @SneakyThrows
    public static void main(String[] args) {
        MyEntity entity = MyEntity.builder().myName("hariom").emailId("hariom@fk.com").build();
        System.out.println("myEntity = " + entity);

        System.out.println("asString = " + new ObjectMapper().writeValueAsString(entity));
    }
}
