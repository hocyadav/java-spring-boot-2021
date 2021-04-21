package io.hari.multithreading.blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ExamUserThread {

    public static void main(String[] args) {

        BlockingQueue<String> questionDb = new ArrayBlockingQueue<>(10);

        //producer : exam thread
        final Thread examThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    final String question = "question : " + i;
                    questionDb.put(question);
                    System.out.println("posting question = " + questionDb);
                    Thread.sleep(30 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "exam-thread");
        examThread.start();

        //consumer - user thread
        final Thread userThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    final String question = questionDb.take();
                    System.out.println("solving question : " + question);
                    Thread.sleep(30 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "user-thread");
        userThread.start();
    }
}
