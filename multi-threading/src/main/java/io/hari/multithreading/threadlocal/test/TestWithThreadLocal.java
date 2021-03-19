package io.hari.multithreading.threadlocal.test;

import lombok.SneakyThrows;

/**
 * @Author Hariom Yadav
 * @create 19-03-2021
 */
public class TestWithThreadLocal {
    @SneakyThrows
    public static void main(String[] args) {
        MyRunnable_ThreadLocalVariable myRunnable = new MyRunnable_ThreadLocalVariable();
        final Thread thread1 = new Thread(myRunnable);
        final Thread thread2 = new Thread(myRunnable);
        thread1.start();
        thread2.start();

        thread1.join();//wait for this thread to terminate
        thread2.join();//wait for this thread to terminate
    }
}
/**
 Thread-1 -  localVariable = 6
 Thread-0 -  localVariable = 39
 */
