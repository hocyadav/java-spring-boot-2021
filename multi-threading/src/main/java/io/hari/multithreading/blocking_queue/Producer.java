package io.hari.multithreading.blocking_queue;

import lombok.SneakyThrows;

import java.util.concurrent.BlockingQueue;

/**
 * @Author Hariom Yadav
 * @create 22-03-2021
 */
public class Producer implements Runnable{
    private BlockingQueue<String> blockingQueue;

    public Producer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            final long currentTimeMillis = System.currentTimeMillis();
            blockingQueue.put(""+currentTimeMillis);
            System.out.println("blockingQueue = " + blockingQueue);
            Thread.sleep(1000);
        }
    }
}
