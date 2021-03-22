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
class MyBQueue_3_sync {
    Queue<Integer> queue;
    int size;
    Object condition1 = new Object();
    Object condition2 = new Object();

    public MyBQueue_3_sync(int size) {
        queue = new LinkedList<>();
        this.size = size;
    }

    @SneakyThrows
    public void put(int data) {
        synchronized (condition1) {//condition1 wait should be inside synchronized with condition1
            while (queue.size() == size) condition1.wait(); //wait/goto sleep mode, when queue is full
        }
        queue.add(data);
        synchronized (condition2) {//condition2 wait should be inside synchronized with condition2
            condition2.notifyAll();//awake other thread that are sleeping
        }
        System.out.println("queue = " + queue);
    }

    @SneakyThrows
    public Integer take() {
        synchronized (condition2) { //condition2 wait should be inside synchronized with condition2
            while (queue.size() == 0) condition2.wait(); //wait when queue is empty
        }
        final Integer remove = queue.remove();
        synchronized (condition1) { //condition1 wait should be inside synchronized with condition1
            condition1.notifyAll();
        }
        return remove;
    }
}

public class MyBlockingQueueImpl_ThreadSafe_3_UsingObject_synchronized {// working : NOTE : synchronized should be used with wait or notify
// one producer one consumer working fine, 2 producer 2 consumer sometime throwing error %
    @SneakyThrows
    public static void main(String[] args) {
        MyBQueue_3_sync myBQueue3_sync = new MyBQueue_3_sync(3);

        final Thread producerThread = new Thread(() -> {
            while (true) {
                myBQueue3_sync.put(new Random().nextInt());
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
                myBQueue3_sync.put(new Random().nextInt());
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
                final Integer take = myBQueue3_sync.take();
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
                final Integer take = myBQueue3_sync.take();
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
 * queue = [1565777779]
 * queue = [1565777779, -1491494108]
 * queue = [1565777779, -1491494108, 2043514703]
 * queue = [-1491494108, 2043514703, -1807935451]
 * take = 1565777779
 * take = -1491494108
 * queue = [2043514703, -1807935451, -943931102]
 * take = 2043514703
 * queue = [-1807935451, -943931102, -1765829188]
 */
