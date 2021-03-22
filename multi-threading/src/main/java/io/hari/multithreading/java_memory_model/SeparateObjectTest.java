package io.hari.multithreading.java_memory_model;

/**
 * @Author Hariom Yadav
 * @create 22-03-2021
 */
public class SeparateObjectTest {
    public static void main(String[] args) {
        Runnable runnable1 = new MyRunnable();
        Runnable runnable2 = new MyRunnable();

        final Thread thread1 = new Thread(runnable1, "Thread 1");
        final Thread thread2 = new Thread(runnable2, "Thread 2");
        thread1.start();
        thread2.start();
    }
}
/**
 Thread 1 1000000
 Thread 2 1000000
 */
