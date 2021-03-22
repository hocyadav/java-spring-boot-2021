package io.hari.multithreading.java_memory_model;

/**
 * @Author Hariom Yadav
 * @create 22-03-2021
 */
public class MyRunnable implements Runnable{
    int count = 0;
    @Override
    public void run() {
        for (int i = 0; i < 1_000_000; i++) {
            count++;
        }
        System.out.println(Thread.currentThread().getName() +" "+count);
    }
}
