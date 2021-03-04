package io.hari.multithreading.sync;

/**
 * @Author Hariom Yadav
 * @create 04-03-2021
 * simple class for sync task -> convert to async
 * how to run async class -> create a obj and send to Thread obj and start thread
 * thread object takes Runnable class obj, not normal class obj
 * Step 1: add impl Runnable
 * Step 2: add logic that I want to run async in run method of Runnable
 */
public class MySyncTaskToAync implements Runnable{
    public void foo() {// some async tasks
        System.out.println("some async CPU/DB/HTTP... task \n");
        return;
    }

    @Override
    public void run() {//pass async task
        System.out.println("thread name --> "+Thread.currentThread().getName());
        foo();
    }
}
