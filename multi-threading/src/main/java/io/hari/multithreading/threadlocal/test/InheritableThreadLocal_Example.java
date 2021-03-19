package io.hari.multithreading.threadlocal.test;

import lombok.SneakyThrows;

/**
 * @Author Hariom Yadav
 * @create 19-03-2021
 */
public class InheritableThreadLocal_Example {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

        final Thread parentThread1 = new Thread(() -> {
            System.out.println("Thread 1");

            threadLocal.set("thread 1 - threadlocal");
            inheritableThreadLocal.set("thread 1 - inheritable threadlocal");

            System.out.println("threadLocal = " + threadLocal.get());
            System.out.println("inheritableThreadLocal = " + inheritableThreadLocal.get());

            final Thread childThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("child thread");
                    System.out.println("threadLocal = " + threadLocal.get());//we cant access parent value inside child thread
                    System.out.println("inheritableThreadLocal = " + inheritableThreadLocal.get());// we can access parent value inside child thread
                }
            });
            childThread.start();
        });
        parentThread1.start();

        final Thread thread2 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(3000);
                System.out.println("thread 2");

                System.out.println("threadLocal = " + threadLocal.get());//thread 2 has not set any value in thread local so null
                System.out.println("inheritableThreadLocal = " + inheritableThreadLocal.get());// -- "" ---
            }
        });
        thread2.start();

    }
}
/**
 Thread 1
 threadLocal = thread 1 - threadlocal
 inheritableThreadLocal = thread 1 - inheritable threadlocal

 child thread
 threadLocal = null
 inheritableThreadLocal = thread 1 - inheritable threadlocal

 thread 2
 threadLocal = null
 inheritableThreadLocal = null
 */
