package june24.producerConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ProducerConsumeer {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<Integer> qq = new ArrayBlockingQueue<>(100);
		AtomicInteger atomicInteger = new AtomicInteger(0);
		
		//create runnable obj
		final Runnable producer = () -> {
			while(true) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				int val = atomicInteger.incrementAndGet();
				//System.out.println("add new data to producer : "+val);
				qq.add(val);
				System.out.println("producer "+qq);
			}
		};
		//m1 using thread - working
		//start runnable obj by sending inside thread
		//new Thread(producer).start();
		//new Thread(producer).start();
		
		//m2
		ExecutorService service = Executors.newFixedThreadPool(10);
		service.submit(producer);
		service.submit(producer);//starting 2nd producer thread
		
		
		
		final Runnable consumer = () -> {
			while(true) {
				Integer t;
				try {
					Thread.sleep(5000);
					t = qq.take();
					System.out.println("consumer "+qq);
					//System.out.println("get data from consumer : "+t);
				} catch (InterruptedException e) {e.printStackTrace();}
			}
		};
		//m1 using thread - working
		//new Thread(consumer).start();
		//new Thread(consumer).start();
		
		//m2
		ExecutorService service2 = Executors.newFixedThreadPool(10);
		service2.submit(consumer);
		service2.submit(consumer);//starting 2nd consumer thread
		
		Thread.sleep(2000);
	}
}
