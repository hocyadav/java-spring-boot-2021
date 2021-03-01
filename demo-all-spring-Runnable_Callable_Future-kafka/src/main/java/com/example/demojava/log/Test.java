package com.example.demojava.log;

import lombok.extern.slf4j.Slf4j;

/**
 * @author HariomYadav
 * @since 09/11/20
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        final String value1 = "hariom yadav";
        final String value2 = "chandan yadav";
        log.info("info msg {} description {}", value1, value2);
    }
}
