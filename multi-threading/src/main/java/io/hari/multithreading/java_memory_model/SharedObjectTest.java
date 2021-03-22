package io.hari.multithreading.java_memory_model;

/**
 * @Author Hariom Yadav
 * @create 22-03-2021
 */
public class SharedObjectTest {
    public static void main(String[] args) {
        final Runnable runnable1 = new MyRunnable();
        final Thread thread1 = new Thread(runnable1, "Thread 1");
        final Thread thread2 = new Thread(runnable1, "Thread 2");
        thread1.start();
        thread2.start();
    }
}
/**
 Thread 1 1070862
 Thread 2 1070862
 */
