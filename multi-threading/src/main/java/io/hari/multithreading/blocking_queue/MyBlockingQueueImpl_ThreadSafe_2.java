package io.hari.multithreading.blocking_queue;

import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Hariom Yadav
 * @create 22-03-2021
 */
class MyBQueue_2 {
    Queue<Integer> queue;
    int size;
    Lock lock = new ReentrantLock(true);

    public MyBQueue_2(int size) {
        queue = new LinkedList<>();
        this.size = size;
    }

    public void put(int data) {
        lock.lock();

        queue.add(data);
        System.out.println("queue = " + queue);

        lock.unlock();
    }

    public Integer take() {
        lock.lock();

        final Integer remove = queue.remove();

        lock.unlock();
        return remove;
    }
}

public class MyBlockingQueueImpl_ThreadSafe_2 {//working but not using size check

    @SneakyThrows
    public static void main(String[] args) {
        MyBQueue_2 myBQueue2 = new MyBQueue_2(3);

        final Thread producerThread = new Thread(() -> {
            while (true) {
                myBQueue2.put(new Random().nextInt());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producerThread.start();
        Thread.sleep(1000);

        final Thread consumerThread = new Thread(() -> {
            while (true) {
                final Integer take = myBQueue2.take();
                System.out.println("take = " + take);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        consumerThread.start();
    }
}
/**
 queue = [626995569]
 take = 626995569
 queue = [-382842791]
 queue = [-382842791, 67756798]
 take = -382842791
 queue = [67756798, -503569913]
 queue = [67756798, -503569913, 73231395]
 */
