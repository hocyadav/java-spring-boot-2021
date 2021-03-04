package io.hari.multithreading;

import io.hari.multithreading.sync.MySyncTask;
import io.hari.multithreading.sync.MySyncTaskToAync;
import io.hari.multithreading.sync.MySyncTaskToAync_Callable;

import java.util.List;
import java.util.concurrent.*;


public class MultiThreadingApplication {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main thread name : --> " + Thread.currentThread().getName());//main
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

        //TODO: different ways of start and stop thread pool
        //TODO : below m1 m2 and m3 are same
        //m1 - fixed thread pool
        final ExecutorService threadPoolService = Executors.newFixedThreadPool(10);
        //m2 - fixed thread pool
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                10,
                10,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        //m3 - we can store ExecutorService
        final ExecutorService threadPoolExecutor2 = new ThreadPoolExecutor(
                10,
                10,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());//link list since we have fixed worker pool so task obj can grow more,
                                                    // if we take Array then we can get exception after queue is full

        // TODO : below both are same
        final ExecutorService cacheWorkerPool = Executors.newCachedThreadPool();
        final ThreadPoolExecutor cacheWorkerPool2 = new ThreadPoolExecutor(
                0, //initial workers pool is 0
                Integer.MAX_VALUE, // max worker this thread pool can create, in worst case this can create as num of task in queue
                60L, //after 60 sec if any thread is not working then kill that thread, ur fire from company :D
                TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());// store only one task in Queue

        //TODO : scheduled or delayed thread pool
        final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(10);
        scheduledThreadPool.schedule(
                syncTaskToAync2, //sending Runnable task to worker thread
                10L, // start executing after 10 sec and only execute one time,
                // this looks same as fixed thread pool both will run at one time and here only run after some time, procrastination
                TimeUnit.SECONDS);

        scheduledThreadPool.scheduleAtFixedRate(
                syncTaskToAync2, //this is task object
                15L, // first task start after 15sec when pool starts
                10L, // then after 10 sec it will start above task again, its possible that single task may take more than that 10 sec
                TimeUnit.SECONDS);

        scheduledThreadPool.scheduleWithFixedDelay(
                syncTaskToAync2, // async task
                15L, // 1st task it will start 15 sec
                10L, // when last task will complete donesnt matter how much time it will take,
                        // then after that it will start new above runnable task after 10 sec
                TimeUnit.SECONDS);

        // TODO : stop thread pool
        threadPoolExecutor.shutdown();// send signal to start shutdown, it will complete all in progress task +  task that are in queue + it will not take new task in queue throw exception
        final boolean shutdown = threadPoolExecutor.isShutdown();//return true since above we have called shutdown method
        System.out.println("shutdown = " + shutdown);

//        threadPoolExecutor.submit(syncTaskToAync2);//throw rejected exception, coz we called shutdown method and it will not take any new task in queue

        final List<Runnable> runnables = threadPoolExecutor.shutdownNow();// complete in progress task + return task that are present in Queue, we can do other operation with pending tasks
        System.out.println("runnables.size() = " + runnables.size());

        //TODO : async task that returns
        MySyncTaskToAync_Callable asyncTask_Callable = new MySyncTaskToAync_Callable();
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        final Future<Integer> future = executorService.submit(asyncTask_Callable);
        final Integer integer = future.get();
        System.out.println("integer = " + integer);


    }
}
