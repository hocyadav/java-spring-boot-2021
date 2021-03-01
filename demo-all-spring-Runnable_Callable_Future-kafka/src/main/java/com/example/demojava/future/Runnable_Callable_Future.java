package com.example.demojava.future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author HariomYadav
 * @since 24/10/20
 */
@Slf4j
public class Runnable_Callable_Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        //        final ExecutorService executorService = Executors.newFixedThreadPool(4);//pool size 4
        final ExecutorService executorService = Executors.newSingleThreadExecutor();//pool size 1
        final MyTask runnableTask = new MyTask();
        //m1. execute will take only runnable
        executorService.execute(runnableTask);
        //m2. submit takes runnable & callable
        executorService.submit(runnableTask);
        executorService.shutdown();//optional - makes shutdown service

        final ExecutorService executorService1 = Executors.newFixedThreadPool(2);
        final MyTask2 callableTask = new MyTask2();
        final Future<Integer> future = executorService1.submit(callableTask);
        //do some operation for 3 sec
        //...
        System.out.println("future.isDone() = " + future.isDone());
        final Integer result = future.get();//block for 3 sec
        System.out.println("future result = " + result);
        System.out.println("future.isDone() = " + future.isDone());
        executorService1.shutdown();//optional

        //wrap callable with future task class
        final ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        final MyTask2 callableTask2 = new MyTask2();
        FutureTask<Integer> futureTask = new FutureTask<>(callableTask2);
        executorService2.submit(futureTask);
//
//        //M1 - working
//        futureTask.run();//call internally callable to complete task
//        final Integer integer = futureTask.get(1, TimeUnit.MILLISECONDS);//get in given time interval or throw exception
//        System.out.println("result = " + integer);
//
//        //M2 working - exception since callable complete in 3 sec and expecting result in 2 sec
//        final Integer integer2 = futureTask.get(2000, TimeUnit.MILLISECONDS);//get in given time interval or throw exception
//        System.out.println("result = " + integer2);


//        //M3 - working
//        final Integer integer2 = futureTask.get(4000, TimeUnit.MILLISECONDS);//get in given time interval or throw exception
//        System.out.println("result = " + integer2);

        //M4
        Thread.sleep(4000);//do some operation
        System.out.println("futureTask.get() = " + futureTask.get());
        System.out.println("futureTask.isDone() = " + futureTask.isDone());
        futureTask.cancel(false);
        System.out.println("futureTask.isDone() = " + futureTask.isDone());
        System.out.println("futureTask.get() = " + futureTask.get());
        executorService2.shutdown();//optional


        //wrap runnable with future task
        final ExecutorService executorService3 = Executors.newSingleThreadExecutor();
        final MyTask runnableTask2 = new MyTask();
        final FutureTask<String> futureTask1 = new FutureTask<>(runnableTask2, "the result");
        executorService3.submit(futureTask1);
        System.out.println("futureTask1 = " + futureTask1.get());

    }
}

class MyTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Task.run : " + new Random().nextInt());
        System.out.println(" do some operation ");
    }
}

class MyTask2 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.err.println("MyTask2.call");
        Thread.sleep(3000);
        return new Random().nextInt();
    }
}
