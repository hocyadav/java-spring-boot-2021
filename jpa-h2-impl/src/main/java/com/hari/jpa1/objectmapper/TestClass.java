package com.hari.jpa1.objectmapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

/**
 * @Author Hariom Yadav
 * @create 5/13/2021
 */
public class TestClass {
    @SneakyThrows
    @Test
    public void objectMapperTest() {//working to and from
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        final Order1 order1 = Order1.builder()
                .name("hariom")
                .email("nasamind@gmail.com")
                .country("india")
                .state("KA")
                .city("bangalore")
                .location("marathelli")
                .zip(560037)
                .build();

        final String json = objectMapper.writeValueAsString(order1);
        System.out.println("json = " + json);

        System.out.println("order1 = " + order1);


        final Order2 convertValue = objectMapper.convertValue(order1, Order2.class);
        System.out.println("convertValue = " + convertValue);

        final Order2 convertValue2 = objectMapper.convertValue(order1, new TypeReference<Order2>(){});
        System.out.println("convertValue2 = " + convertValue2);

        final Order3 order3 = objectMapper.convertValue(order1, Order3.class);
        order3.setCountry_oror("india");
        System.out.println("order3 = " + order3);

    }
}
