package io.hari.multithreading.threadlocal.test;

import lombok.SneakyThrows;

import java.util.Random;

/**
 * @Author Hariom Yadav
 * @create 19-03-2021
 */
public class MyRunnable implements Runnable{
    Integer localVariable;//data inconsistent coz this is not thread safe
    @SneakyThrows
    @Override
    public void run() {
        localVariable = new Random().nextInt(100);
        System.out.println(Thread.currentThread().getName()+" - "+" localVariable = " + localVariable);
        Thread.sleep(3000);
    }
}
