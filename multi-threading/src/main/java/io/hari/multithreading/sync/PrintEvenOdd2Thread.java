package io.hari.multithreading.sync;

/**
 * @Author Hariom Yadav
 * @create 04-03-2021
 */
public class PrintEvenOdd2Thread {
    int counter = 0;// global counter

    public void evenPrint() throws InterruptedException {
        synchronized (this) {
            while (counter < 10) {// traverse from 1 to N
                while (counter % 2 != 0) { // not even check - then go to wait (means other thread task if that will complete then that will awake me)
                    wait();// this thread will go in sleep mode
                }
                System.out.println("even number : "+counter+" "+Thread.currentThread().getName());
                counter++;
                notify();// awake other thread, notify on same this obj, that means any other thread waiting will wake up
            }
        }
    }

    public void oddPrint() throws InterruptedException {
        synchronized (this) {
            while (counter < 10) {// traverse from 1 to N
                while (counter % 2 != 1) {// not odd check, i.e. even check
                    wait();
                }
                System.out.println("odd number : "+ counter +" "+Thread.currentThread().getName());
                counter++;
                notify();
            }
        }
    }
}
