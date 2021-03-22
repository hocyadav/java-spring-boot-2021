package io.hari.multithreading.blocking_queue;

import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Hariom Yadav
 * @create 22-03-2021
 */
class MyBQueue_3 {
    Queue<Integer> queue;
    int size;
    Lock lock = new ReentrantLock(true);
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();

    public MyBQueue_3(int size) {
        queue = new LinkedList<>();
        this.size = size;
    }

    @SneakyThrows
    public void put(int data) {
        lock.lock();

        while (queue.size() == size) condition1.await(); //wait/goto sleep mode, when queue is full
        queue.add(data);
        condition2.signalAll();//awake other thread that are sleeping
        System.out.println("queue = " + queue);

        lock.unlock();
    }

    @SneakyThrows
    public Integer take() {
        lock.lock();

        while (queue.size() == 0) condition2.await(); //wait when queue is empty
        final Integer remove = queue.remove();
        condition1.signalAll();

        lock.unlock();
        return remove;
    }
}

public class MyBlockingQueueImpl_ThreadSafe_3_usingLock_condition {//working

    @SneakyThrows
    public static void main(String[] args) {
        MyBQueue_3 myBQueue3 = new MyBQueue_3(3);

        final Thread producerThread = new Thread(() -> {
            while (true) {
                myBQueue3.put(new Random().nextInt());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producerThread.start();

        final Thread producerThread2 = new Thread(() -> {
            while (true) {
                myBQueue3.put(new Random().nextInt());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producerThread2.start();

        Thread.sleep(1000);

        final Thread consumerThread = new Thread(() -> {
            while (true) {
                final Integer take = myBQueue3.take();
                System.out.println("take = " + take);
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        consumerThread.start();

        final Thread consumerThread2 = new Thread(() -> {
            while (true) {
                final Integer take = myBQueue3.take();
                System.out.println("take = " + take);
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        consumerThread2.start();
    }
}
/**
 queue = [1565777779]
 queue = [1565777779, -1491494108]
 queue = [1565777779, -1491494108, 2043514703]
 queue = [-1491494108, 2043514703, -1807935451]
 take = 1565777779
 take = -1491494108
 queue = [2043514703, -1807935451, -943931102]
 take = 2043514703
 queue = [-1807935451, -943931102, -1765829188]
 */
