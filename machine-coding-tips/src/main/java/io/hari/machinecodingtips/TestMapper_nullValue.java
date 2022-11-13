package io.hari.machinecodingtips;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class TestMapper_nullValue {

    @SneakyThrows
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper()
                ;

        System.out.println("writeValueAsString = " + objectMapper.writeValueAsString("hariom"));
        System.out.println("writeValueAsString = " + objectMapper.writeValueAsString(""));
        String s = objectMapper.writeValueAsString(null);
        System.out.println("writeValueAsString = " + s);
        System.out.println("empty = " + s.isEmpty());
        System.out.println("null = " + s == null);
        System.out.println("writeValueAsString = " + objectMapper.writeValueAsString("null"));
    }
}
