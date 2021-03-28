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
/**
 even number : 0 Thread-0
 odd number : 1 my-thread-name-odd
 even number : 2 Thread-0
 odd number : 3 my-thread-name-odd
 even number : 4 Thread-0
 odd number : 5 my-thread-name-odd
 even number : 6 Thread-0
 odd number : 7 my-thread-name-odd
 even number : 8 Thread-0
 odd number : 9 my-thread-name-odd
 even number : 10 Thread-0
 */
