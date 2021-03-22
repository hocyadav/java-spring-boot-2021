package io.hari.multithreading.blocking_queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author Hariom Yadav
 * @create 22-03-2021
 */
public class ProducerConsumerTest {
    public static void main(String[] args) {
        final BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
        final Producer producer = new Producer(blockingQueue);
        final Consumer consumer = new Consumer(blockingQueue);

        final Thread producerThread = new Thread(producer);
        final Thread consumerThread = new Thread(consumer);
        producerThread.start();
        consumerThread.start();

        final Thread producerThread2 = new Thread(producer);
        producerThread2.start();

    }
}
/**
consumed = 1616404829250
blockingQueue = [1616404829250]
blockingQueue = [1616404829250]
consumed = 1616404829250
blockingQueue = [1616404830250, 1616404830250]
consumed = 1616404830250
blockingQueue = [1616404830250, 1616404830250]
consumed = 1616404830250
blockingQueue = [1616404831250]
consumed = 1616404831250
consumed = 1616404831250
blockingQueue = [1616404831250, 1616404831250]
blockingQueue = [1616404832250, 1616404832250]
 **/
