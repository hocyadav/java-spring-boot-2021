package march3rd_Office;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 
 * @author Hariom Yadav | 03-Mar-2020
 *	running 1000 task parallel by using thread - expensive operation 
 *	Solution : thread pool --> executor service : upfront create 10 thread and assign 1000 task to them 
 *	If task are extensive IP operation than each thread will take more time to fetch data from db 
 *  so even if thread same as number of cores we can see all threads are in waiting state 
 *  Solution : create more number of threads so that if threads are waiting then other threads can start
 */
public class ThreadBasic_nThreadPool5_IO_IntensiveOperation {
	public static void main(String[] args) {
		System.out.println("Main thread start");
		
		ExecutorService service = Executors.newFixedThreadPool(100);
		for(int i = 0; i < 100; i++) {
			service.execute(new IoIntensiveTask());
		}
		
		System.out.println("Main thread end ");
	}
	
	static class IoIntensiveTask implements Runnable{
		public void run() {
			System.out.println("Thread name "+Thread.currentThread().getName());
			try {
				//System.out.println("Fetching data from db or any IO operations..");
				Thread.sleep(1000);
				System.out.println("completed");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

/**
Main thread start
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
Thread name pool-1-thread-17
Thread name pool-1-thread-18
Thread name pool-1-thread-19
Thread name pool-1-thread-20
Thread name pool-1-thread-21
Thread name pool-1-thread-22
Thread name pool-1-thread-23
Thread name pool-1-thread-24
Thread name pool-1-thread-25
Thread name pool-1-thread-26
Thread name pool-1-thread-27
Thread name pool-1-thread-28
Thread name pool-1-thread-29
Thread name pool-1-thread-30
Thread name pool-1-thread-31
Thread name pool-1-thread-32
Thread name pool-1-thread-33
Thread name pool-1-thread-34
Thread name pool-1-thread-35
Thread name pool-1-thread-36
Thread name pool-1-thread-37
Thread name pool-1-thread-38
Thread name pool-1-thread-39
Thread name pool-1-thread-40
Thread name pool-1-thread-41
Thread name pool-1-thread-42
Thread name pool-1-thread-43
Thread name pool-1-thread-44
Thread name pool-1-thread-45
Thread name pool-1-thread-46
Thread name pool-1-thread-47
Thread name pool-1-thread-48
Thread name pool-1-thread-49
Thread name pool-1-thread-50
Thread name pool-1-thread-51
Thread name pool-1-thread-52
Thread name pool-1-thread-53
Thread name pool-1-thread-54
Thread name pool-1-thread-55
Thread name pool-1-thread-56
Thread name pool-1-thread-57
Thread name pool-1-thread-58
Thread name pool-1-thread-59
Thread name pool-1-thread-60
Thread name pool-1-thread-61
Thread name pool-1-thread-62
Thread name pool-1-thread-63
Thread name pool-1-thread-64
Thread name pool-1-thread-65
Thread name pool-1-thread-66
Thread name pool-1-thread-67
Thread name pool-1-thread-68
Thread name pool-1-thread-69
Thread name pool-1-thread-70
Thread name pool-1-thread-71
Thread name pool-1-thread-72
Thread name pool-1-thread-73
Thread name pool-1-thread-74
Thread name pool-1-thread-75
Thread name pool-1-thread-76
Thread name pool-1-thread-77
Thread name pool-1-thread-78
Thread name pool-1-thread-79
Thread name pool-1-thread-80
Thread name pool-1-thread-81
Thread name pool-1-thread-82
Thread name pool-1-thread-83
Thread name pool-1-thread-84
Thread name pool-1-thread-85
Thread name pool-1-thread-86
Thread name pool-1-thread-87
Thread name pool-1-thread-88
Thread name pool-1-thread-89
Thread name pool-1-thread-90
Thread name pool-1-thread-91
Thread name pool-1-thread-92
Thread name pool-1-thread-93
Thread name pool-1-thread-94
Thread name pool-1-thread-95
Thread name pool-1-thread-96
Thread name pool-1-thread-97
Thread name pool-1-thread-98
Thread name pool-1-thread-99
Main thread end 
Thread name pool-1-thread-100
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
completed
 */
