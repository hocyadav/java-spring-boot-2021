package io.hari.multithreading.semaphore;

import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;

/**
 * @Author Hariom Yadav
 * @create 20-03-2021
 * http://tutorials.jenkov.com/java-util-concurrent/semaphore.html
 */
public class SemaphoreTest {
    @SneakyThrows
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        semaphore.acquire();
        System.out.println("test1");
        semaphore.acquire();//wait if semaphore count is 1
        System.out.println("test2");
        semaphore.release();
        semaphore.release();
        /** output
         test1
         test2
         */

        Semaphore semaphore1 = new Semaphore(1, true);//1st waiting thread will get first semaphore, like FIFO
        semaphore1.acquire();
        System.out.println("semaphore 1");
        semaphore1.release();


    }
}
/**
 test1
 test2
 semaphore 1
 */
