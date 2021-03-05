package io.hari.multithreading.sync;

import io.hari.multithreading.MultiThreadingApplication;
import lombok.SneakyThrows;

/**
 * @Author Hariom Yadav
 * @create 04-03-2021
 * simple class for sync task -> convert to async
 * how to run async class -> create a obj and send to Thread obj and start thread
 * thread object takes Runnable class obj, not normal class obj
 * Step 1: add impl Runnable
 * Step 2: add logic that I want to run async in run method of Runnable
 */
public class MySyncTaskToAync_Semaphore implements Runnable{
    public void foo() {// some async tasks
        System.out.println("semaphore some async slow service operation ... CPU/DB/HTTP... task \n");
        return;
    }

    @SneakyThrows
    public void run() {//pass async task
        System.out.println("thread name --> "+Thread.currentThread().getName());
        MultiThreadingApplication.semaphore.acquire();//slow service call start
        foo();
//        Thread.sleep(2000);
        MultiThreadingApplication.semaphore.release();// slow service call end
    }
}
