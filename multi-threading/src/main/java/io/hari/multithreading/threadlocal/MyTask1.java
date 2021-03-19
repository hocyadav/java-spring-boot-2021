package io.hari.multithreading.threadlocal;

/**
 * @Author Hariom Yadav
 * @create 19-03-2021
 */
public class MyTask1 implements Runnable {
    @Override
    public void run() {
        //some task
        System.out.println("inside runnable...");
    }
}
