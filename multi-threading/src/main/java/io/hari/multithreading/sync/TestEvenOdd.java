package io.hari.multithreading.sync;

import lombok.SneakyThrows;

/**
 * @Author Hariom Yadav
 * @create 04-03-2021
 */
public class TestEvenOdd {
    public static void main(String[] args) {
        PrintEvenOdd2Thread evenOdd2Thread = new PrintEvenOdd2Thread();
        Thread thread1 = new Thread(new Runnable() {
            @SneakyThrows // throw check exception
            public void run() {
                evenOdd2Thread.evenPrint();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @SneakyThrows
            public void run() {
                evenOdd2Thread.oddPrint();
            }
        }, "my-thread-name-odd");

        thread1.start();
        thread2.start();
    }
}
