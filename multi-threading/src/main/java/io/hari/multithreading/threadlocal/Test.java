package io.hari.multithreading.threadlocal;

import com.sun.javafx.css.Rule;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Hariom Yadav
 * @create 19-03-2021
 */
public class Test {
    //global service single obj for single thread, its depend on thread not on class where it is used
    public static ThreadLocal<GlobalService> globalService = new ThreadLocal<GlobalService>(){
        @Override
        protected GlobalService initialValue() {
            return new GlobalService();
        }
        @Override
        public GlobalService get() {
            return super.get();
        }
    };
    public static void main(String[] args) {
        //sync task - using simple class
        final MyTask0 myTask0 = new MyTask0();
        myTask0.foo();

        //async task - using simple class
        new Thread(new Runnable() {
            @Override
            public void run() {
                final MyTask0 myTask01 = new MyTask0();
                myTask0.foo();
            }
        }).start();

        //async task - using runnable class
        final MyTask1 myTask1 = new MyTask1();
        new Thread(myTask1).start();

        //async task + internally uses new object of service class to perform some tasks
        new Thread(new Runnable() {
            @Override
            public void run() {
                final MyTask0_useService task = new MyTask0_useService();
                task.foo();
            }
        }).start();

        //1000 task in BlockingQueue and 10 task executing by thread pool,  1000 global service objects
        final ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            pool.submit(new Runnable() {//1000 new obj of task created , and also 1000 new global service created, problem is we have only 10 pool size so why 1000 so memory wastage
                @Override
                public void run() {
                    final MyTask0_useService task_ = new MyTask0_useService();
                    task_.foo();
                }
            });
        }

        //1000 task , 10 thread pool, 10 global service obj
        ThreadLocal<GlobalService> globalService = new ThreadLocal<GlobalService>(){
            @Override
            protected GlobalService initialValue() {
                return new GlobalService();
            }
            @Override
            public GlobalService get() {
                return super.get();
            }
        };

        final ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    final MyTask0_useService2 task2_ = new MyTask0_useService2();
                    task2_.foo();
                }
            });
        }

    }
}
