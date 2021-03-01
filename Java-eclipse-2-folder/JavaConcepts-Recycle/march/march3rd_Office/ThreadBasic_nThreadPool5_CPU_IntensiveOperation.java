package march3rd_Office;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 
 * @author Hariom Yadav | 03-Mar-2020
 *	running 1000 task parallel by using thread - expensive operation 
 *	Solution : thread pool --> executor service : upfront create 10 thread and assign 1000 task to them 
 *	If task are extensive cpu operation than each thread will take more time so dont create more thread pool,
 *	solution : create number of thread as same as cpu cores, So that at a given time all threads will run in same time
 */
public class ThreadBasic_nThreadPool5_CPU_IntensiveOperation {
	public static void main(String[] args) {
		System.out.println("Main thread start");
		
		//get core count 
		int coreCount = Runtime.getRuntime().availableProcessors();
		System.out.println("CPU cores : "+coreCount);
		ExecutorService service = Executors.newFixedThreadPool(coreCount);
		for(int i = 0; i < 100; i++) {
			service.execute(new CpuIntensiveTask());
		}
		
		System.out.println("Main thread end ");
	}
	
	static class CpuIntensiveTask implements Runnable{
		public void run() {
			System.out.println("Thread name "+Thread.currentThread().getName());
			try {
				//System.out.println("CPU is taking more time to complete this thread task");
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
/**
Main thread start
CPU cores : 16
Thread name pool-1-thread-1
Thread name pool-1-thread-2
Thread name pool-1-thread-3
Thread name pool-1-thread-4
Thread name pool-1-thread-5
Thread name pool-1-thread-6
Thread name pool-1-thread-7
Thread name pool-1-thread-8
Thread name pool-1-thread-9
Thread name pool-1-thread-10
Thread name pool-1-thread-11
Thread name pool-1-thread-12
Thread name pool-1-thread-13
Thread name pool-1-thread-14
Thread name pool-1-thread-15
Thread name pool-1-thread-16
Main thread end 
Thread name pool-1-thread-2
Thread name pool-1-thread-8
Thread name pool-1-thread-5
Thread name pool-1-thread-4
Thread name pool-1-thread-9
Thread name pool-1-thread-7
Thread name pool-1-thread-3
Thread name pool-1-thread-10
Thread name pool-1-thread-6
Thread name pool-1-thread-11
Thread name pool-1-thread-1
Thread name pool-1-thread-13
Thread name pool-1-thread-12
Thread name pool-1-thread-14
Thread name pool-1-thread-15
Thread name pool-1-thread-16
Thread name pool-1-thread-5
Thread name pool-1-thread-15
Thread name pool-1-thread-4
Thread name pool-1-thread-10
Thread name pool-1-thread-13
Thread name pool-1-thread-3
Thread name pool-1-thread-1
Thread name pool-1-thread-2
Thread name pool-1-thread-14
Thread name pool-1-thread-12
Thread name pool-1-thread-6
Thread name pool-1-thread-11
Thread name pool-1-thread-9
Thread name pool-1-thread-8
Thread name pool-1-thread-7
Thread name pool-1-thread-16
Thread name pool-1-thread-15
Thread name pool-1-thread-13
Thread name pool-1-thread-2
Thread name pool-1-thread-12
Thread name pool-1-thread-9
Thread name pool-1-thread-8
Thread name pool-1-thread-4
Thread name pool-1-thread-1
Thread name pool-1-thread-10
Thread name pool-1-thread-5
Thread name pool-1-thread-11
Thread name pool-1-thread-7
Thread name pool-1-thread-14
Thread name pool-1-thread-6
Thread name pool-1-thread-3
Thread name pool-1-thread-16
Thread name pool-1-thread-2
Thread name pool-1-thread-13
Thread name pool-1-thread-8
Thread name pool-1-thread-12
Thread name pool-1-thread-10
Thread name pool-1-thread-4
Thread name pool-1-thread-15
Thread name pool-1-thread-9
Thread name pool-1-thread-1
Thread name pool-1-thread-7
Thread name pool-1-thread-3
Thread name pool-1-thread-6
Thread name pool-1-thread-14
Thread name pool-1-thread-11
Thread name pool-1-thread-5
Thread name pool-1-thread-16
Thread name pool-1-thread-13
Thread name pool-1-thread-8
Thread name pool-1-thread-2
Thread name pool-1-thread-12
Thread name pool-1-thread-10
Thread name pool-1-thread-4
Thread name pool-1-thread-9
Thread name pool-1-thread-1
Thread name pool-1-thread-15
Thread name pool-1-thread-6
Thread name pool-1-thread-14
Thread name pool-1-thread-5
Thread name pool-1-thread-7
Thread name pool-1-thread-3
Thread name pool-1-thread-11
Thread name pool-1-thread-16
Thread name pool-1-thread-15
Thread name pool-1-thread-12
Thread name pool-1-thread-9
Thread name pool-1-thread-8

 */
