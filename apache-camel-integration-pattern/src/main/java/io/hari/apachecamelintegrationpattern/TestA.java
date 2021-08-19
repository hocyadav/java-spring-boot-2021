package io.hari.apachecamelintegrationpattern;

import lombok.extern.java.Log;

@Log
public class TestA {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        log.info("TestA.main");
    }
}
