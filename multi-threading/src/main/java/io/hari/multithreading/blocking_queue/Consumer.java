package io.hari.multithreading.blocking_queue;

import lombok.SneakyThrows;

import java.util.concurrent.BlockingQueue;

/**
 * @Author Hariom Yadav
 * @create 22-03-2021
 */
public class Consumer implements Runnable{
    private BlockingQueue<String> blockingQueue;

    public Consumer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            final String take = blockingQueue.take();
            System.out.println("consumed = " + take);
        }
    }
}
