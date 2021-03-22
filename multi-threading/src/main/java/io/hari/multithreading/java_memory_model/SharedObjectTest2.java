package io.hari.multithreading.java_memory_model;

/**
 * @Author Hariom Yadav
 * @create 22-03-2021
 */
public class SharedObjectTest2 {
    public static void main(String[] args) {
        final Runnable runnable1 = new MyRunnable2();
        final Thread thread1 = new Thread(runnable1, "Thread 1");
        final Thread thread2 = new Thread(runnable1, "Thread 2");
        thread1.start();
        thread2.start();
        //the thread that run last print 2 000 000 value
    }
}
/**
 Thread 1 1312372
 Thread 2 2000000
 */
