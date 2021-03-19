package io.hari.multithreading.threadlocal.test;

import lombok.SneakyThrows;

/**
 * @Author Hariom Yadav
 * @create 19-03-2021
 */
public class TestWithAtomicVariable {
    @SneakyThrows
    public static void main(String[] args) {
        MyRunnable_AtomicVariable myRunnable = new MyRunnable_AtomicVariable();
        final Thread thread1 = new Thread(myRunnable);
        final Thread thread2 = new Thread(myRunnable);
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
/**
 Thread-0 -  localVariable = 92
 Thread-1 -  localVariable = 92
 */
