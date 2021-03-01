package march3rd_Office;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadPool_type2_cachedThreadPool {
	public static void main(String[] args) {
		//fixed 10 thread pool
		ExecutorService service = Executors.newCachedThreadPool();
		
		//execute task
		for(int i = 0; i < 100000; i++) {
			service.execute(new Task());
		}
		System.out.println("Thread main : "+Thread.currentThread().getName());
	}
	
	static class Task implements Runnable{
		@Override
		public void run() {
			System.out.println("Thread name :"+Thread.currentThread().getName());
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

