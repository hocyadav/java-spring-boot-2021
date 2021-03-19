package io.hari.multithreading.threadlocal.test;

import lombok.SneakyThrows;

import java.util.Random;

/**
 * @Author Hariom Yadav
 * @create 19-03-2021
 * http://tutorials.jenkov.com/java-concurrency/threadlocal.html
 * https://www.youtube.com/watch?v=a_BoqsnVR2U
 */
public class MyRunnable_ThreadLocalVariable implements Runnable{
    ThreadLocal<Integer> localVariable = new ThreadLocal<>();//thread safe and each thread have own memory space in ThreadLocal context

    @SneakyThrows
    @Override
    public void run() {
        localVariable.set(new Random().nextInt(100));
        System.out.println(Thread.currentThread().getName()+" - "+" localVariable = " + localVariable.get());
        Thread.sleep(3000);
    }
}
