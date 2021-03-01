package june24.producerConsumer.lockCondition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Test_LockCondition {
	public static void main(String[] args) {
		MyBlockingQueue_LockCondition<Integer> qq = new MyBlockingQueue_LockCondition<Integer>(10);
		AtomicInteger atomicInteger = new AtomicInteger(0);
		
		final Runnable producer = () -> {
			while(true) {
				try {
					Thread.sleep(3000);
					int i = atomicInteger.incrementAndGet();
					System.out.println("producer : "+i);
					qq.put(i);
					System.out.println(qq.qq);
				} catch (InterruptedException e) {e.printStackTrace();}
			}
		};
		//m1 using thread - working
		//new Thread(producer).start();
		//new Thread(producer).start();
		
		//m2 using thread pool - working
		ExecutorService service = Executors.newFixedThreadPool(10);
		service.submit(producer);
		service.submit(producer);//starting 2nd producer thread
		
		
		final Runnable consumer = () -> {
			while(true) {
				Integer val;
				try {
					Thread.sleep(5000);
					val = qq.take();
					System.out.println("consumer : "+val);
				} catch (InterruptedException e) {e.printStackTrace();}
			}
		};
		//new Thread(consumer).start();
		//new Thread(consumer).start();
		
		ExecutorService service2 = Executors.newFixedThreadPool(10);
		service2.submit(consumer);
		service2.submit(consumer);//starting 2nd consumer thread
	}
}
