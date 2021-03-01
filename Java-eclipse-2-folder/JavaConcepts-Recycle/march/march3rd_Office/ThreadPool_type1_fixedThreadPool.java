package march3rd_Office;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool_type1_fixedThreadPool {
	public static void main(String[] args) {
		//fixed 10 thread pool
		ExecutorService service = Executors.newFixedThreadPool(10);
		
		//execute task
		for(int i = 0; i < 100; i++) {
			service.execute(new Task());
		}
		System.out.println("Thread main : "+Thread.currentThread().getName());
	}
	
	static class Task implements Runnable{
		@Override
		public void run() {
			System.out.println("Thread name :"+Thread.currentThread().getName());
		}
	}
}
