package io.hari.multithreading.blocking_queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Hariom Yadav
 * @create 22-03-2021
 */
class MyBlockingQueue {
    Queue<Integer> queue;
    int size;
    public MyBlockingQueue(int size) {
        queue = new LinkedList<>();
        this.size = size;
    }

    public void put(int data) {
        queue.add(data);
    }

    public Integer take() {
        return queue.remove();
    }

}
public class MyBlockingQueueImpl_NoThreadSafe {
    public static void main(String[] args) {
        MyBlockingQueue myBlockingQueue = new MyBlockingQueue(3);
        myBlockingQueue.put(10);
        myBlockingQueue.put(20);
        System.out.println("queue = " + myBlockingQueue.queue);
        final Integer take = myBlockingQueue.take();
        System.out.println("queue = " + myBlockingQueue.queue);
    }
}
/**
 queue = [10, 20]
 queue = [20]
 */
