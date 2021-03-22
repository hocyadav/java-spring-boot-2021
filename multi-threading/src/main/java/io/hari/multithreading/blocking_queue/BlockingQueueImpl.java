package io.hari.multithreading.blocking_queue;

import lombok.SneakyThrows;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author Hariom Yadav
 * @create 22-03-2021
 */
public class BlockingQueueImpl {
    @SneakyThrows
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(1);
        blockingQueue.put(1);
        System.out.println("blockingQueue = " + blockingQueue);
        final Integer take = blockingQueue.take();
        System.out.println("blockingQueue = " + blockingQueue);
        blockingQueue.put(2);
        System.out.println("blockingQueue = " + blockingQueue);
        System.out.println("take = " + take);
    }
}
/**
 * blockingQueue = [1]
 * blockingQueue = []
 * blockingQueue = [2]
 * take = 1
 */
