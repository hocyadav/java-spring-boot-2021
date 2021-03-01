package june28thInterruptThreadAfter10min;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class StopTask_using1Thread_interrupt_Callable {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService threadPool = Executors.newFixedThreadPool(2);
		threadPool.submit(new Runnable() {
			@Override
			public void run() {
				while (!Thread.currentThread().isInterrupted()) {
					System.out.println("task");
				}
			}
		});
		Thread.sleep(1000);
		//threadPool.shutdown();//
		threadPool.shutdownNow();//internally it will call thread interrutpt
	}
}

//class Task implements Runnable{//default same package
//	@Override
//	public void run() {
//		System.out.println("task");
//	}
//}

