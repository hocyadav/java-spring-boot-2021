package io.hari.multithreading.blocking_queue;

import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @Author Hariom Yadav
 * @create 22-03-2021
 */
class MyBQueue {
    Queue<Integer> queue;
    int size;

    public MyBQueue(int size) {
        queue = new LinkedList<>();
        this.size = size;
    }

    public void put(int data) {
        synchronized (this) {
            queue.add(data);
            System.out.println("queue = " + queue);
        }
    }

    public Integer take() {
        synchronized (this) {
            final Integer remove = queue.remove();
            return remove;
        }
    }
}

public class MyBlockingQueueImpl_ThreadSafe {//working but not using size check
    @SneakyThrows
    public static void main(String[] args) {
        MyBQueue myBQueue = new MyBQueue(3);

        final Thread producerThread = new Thread(() -> {
            while (true) {
                myBQueue.put(new Random().nextInt());
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
                final Integer take = myBQueue.take();
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
 queue = [211736953]
 queue = [211736953, 838814691]
 take = 211736953
 queue = [838814691, -1257995359]
 queue = [838814691, -1257995359, 1383360483]
 take = 838814691
 queue = [-1257995359, 1383360483, -668595466]
 take = -1257995359
 queue = [1383360483, -668595466, -308932321]
 queue = [1383360483, -668595466, -308932321, 256518009]
 take = 1383360483
 */
