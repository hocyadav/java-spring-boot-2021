package sept20th;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerIMPL {
    public static void main(String[] args) {
        final BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(10);

        final Runnable runnable = new Runnable() {
            public void run() {
                while (true) {
                	System.out.println("pro : "+blockingQueue);
                    blockingQueue.add("hari");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        final Runnable runnable1 = new Runnable() {
            public void run() {
                while (true) {
                    try {
                    	System.out.println("con : "+blockingQueue);
                        String take = blockingQueue.take();
                        System.out.println("take = " + take);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        Thread thread = new Thread(runnable);
        Thread thread1 = new Thread(runnable1);

        thread.start();
        thread1.start();

    }

}
