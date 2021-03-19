package io.hari.multithreading.threadlocal.test;

import lombok.SneakyThrows;

/**
 * @Author Hariom Yadav
 * @create 19-03-2021
 * http://tutorials.jenkov.com/java-concurrency/threadlocal.html
 */
public class TestWithoutThreadLocal {
    @SneakyThrows
    public static void main(String[] args) {
        final MyRunnable myRunnable = new MyRunnable();
        final Thread thread1 = new Thread(myRunnable);
        final Thread thread2 = new Thread(myRunnable);
        //both threads will assign different values
        thread1.start();
//        Thread.sleep(1000); //if we comment then they can run simultaneously and can get same value that is set by thread 1
        thread2.start();

        thread1.join();//wait for thread1 to terminate
        thread2.join();//wait for thread2 to terminate

    }
}
/**
 Thread-1 -  localVariable = 99
 Thread-0 -  localVariable = 99

 if we add timer between thread 1 and thread 2
 Thread-0 -  localVariable = 61
 Thread-1 -  localVariable = 63
 */
