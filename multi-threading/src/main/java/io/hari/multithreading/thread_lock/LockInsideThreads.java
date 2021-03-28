package io.hari.multithreading.thread_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Hariom Yadav
 * @create 28-03-2021
 */
public class LockInsideThreads {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock(true);
        final Thread thread1 = new Thread(() -> {
            lock.lock();
            System.out.println("thread1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { }
            lock.unlock();
        });
        final Thread thread2 = new Thread(() -> {
            lock.lock();
            System.out.println("thread2");
            lock.unlock();
        });
        thread1.start();
        thread2.start();
    }
}
/**
 thread1
 thread2
 */
