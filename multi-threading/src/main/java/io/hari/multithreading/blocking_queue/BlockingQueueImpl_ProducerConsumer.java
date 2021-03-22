package io.hari.multithreading.blocking_queue;

import lombok.SneakyThrows;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author Hariom Yadav
 * @create 22-03-2021
 */
public class BlockingQueueImpl_ProducerConsumer {
    @SneakyThrows
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);

        //producer
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    blockingQueue.put(i);
                    System.out.println("put blockingQueue = " + blockingQueue);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //consumer
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    final Integer take = blockingQueue.take();
//                    System.out.println("take = " + take);
                    System.out.println("take "+ take +" blockingQueue = " + blockingQueue);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
/**
 put blockingQueue = [0]
 take 0 blockingQueue = []
 put blockingQueue = [1]
 take 1 blockingQueue = []
 put blockingQueue = [2]
 put blockingQueue = [2, 3]
 take 2 blockingQueue = [3]
 put blockingQueue = [3, 4]
 put blockingQueue = [3, 4, 5]
 take 3 blockingQueue = [4, 5]
 put blockingQueue = [4, 5, 6]
 put blockingQueue = [4, 5, 6, 7]
 take 4 blockingQueue = [5, 6, 7]
 put blockingQueue = [5, 6, 7, 8]
 put blockingQueue = [5, 6, 7, 8, 9]
 take 5 blockingQueue = [6, 7, 8, 9]
 take 6 blockingQueue = [7, 8, 9]
 take 7 blockingQueue = [8, 9]
 take 8 blockingQueue = [9]
 take 9 blockingQueue = []
 */
