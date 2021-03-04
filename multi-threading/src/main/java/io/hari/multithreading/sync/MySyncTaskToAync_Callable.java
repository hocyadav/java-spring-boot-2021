package io.hari.multithreading.sync;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * @Author Hariom Yadav
 * @create 04-03-2021
 * simple class for sync task -> convert to async
 * how to run async class -> create a obj and send to Thread obj and start thread
 * thread object takes Callable class obj, not normal class obj
 * Step 1: add impl callable
 * Step 2: add logic that I want to run async in run method of Runnable
 */
public class MySyncTaskToAync_Callable implements Callable<Integer> {
    public void foo() {// some async tasks
        System.out.println("some async CPU/DB/HTTP... task \n");
        return;
    }
    @Override
    public Integer call() throws Exception {
        System.out.println("callable thread name --> "+Thread.currentThread().getName());
        foo();
        return new Random().nextInt();
    }
}
