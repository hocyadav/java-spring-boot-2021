package io.hari.multithreading.threadlocal.test;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author Hariom Yadav
 * @create 19-03-2021
 */
public class MyRunnable_AtomicVariable implements Runnable{
    AtomicReference<Integer> localVariable = new AtomicReference<>();//thread safe but at a time only one thread can access or use this value
    @SneakyThrows
    @Override
    public void run() {
        localVariable.set(new Random().nextInt(100));
        System.out.println(Thread.currentThread().getName()+" - "+" localVariable = " + localVariable.get());
        Thread.sleep(3000);
    }
}
