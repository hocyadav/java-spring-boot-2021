package io.hari.multithreading;

import io.hari.multithreading.sync.MySyncTask;
import io.hari.multithreading.sync.MySyncTaskToAync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MultiThreadingApplication {

    public static void main(String[] args) {
        //test sync tasks, create a class obj -> call method
        MySyncTask syncTask = new MySyncTask();
        syncTask.foo();

        //test async tasks, create a class obj -> call method is done by thread
        MySyncTaskToAync syncTaskToAync = new MySyncTaskToAync();
        Thread thread1 = new Thread(syncTaskToAync);//default thread name thread-0
        thread1.start();

        //thread name
        Thread thread2 = new Thread(syncTaskToAync);
        thread2.setName("sync-to-async");//change thread name , optional
        thread2.start();


        //run 1o async tasks
        MySyncTaskToAync syncTaskToAync1 = new MySyncTaskToAync();
        for (int i = 0; i <= 10; i++) {
            Thread thread_ = new Thread(syncTaskToAync1);
            thread_.start();
        }

        //if task is expensive then above one is not good approach, solution create thread pool and send task to pool
        MySyncTaskToAync syncTaskToAync2 = new MySyncTaskToAync();
        ExecutorService threadPool = Executors.newFixedThreadPool(5);//thread pool = worker pool
        threadPool.submit(syncTaskToAync2);//sending 10 tasks(Runnable obj) to Blocking Queue and worker will pick from Queue
        threadPool.submit(syncTaskToAync2);
        threadPool.submit(syncTaskToAync2);
        threadPool.submit(syncTaskToAync2);
        threadPool.submit(syncTaskToAync2);
        threadPool.submit(syncTaskToAync2);
        threadPool.submit(syncTaskToAync2);
        threadPool.submit(syncTaskToAync2);
        threadPool.submit(syncTaskToAync2);
        threadPool.submit(syncTaskToAync2);
        //we can use for look to send 10 tasks to Blocking Queue







    }
}
