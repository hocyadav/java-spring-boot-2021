package march3rd_Office;
/**
 * 
 * @author Hariom Yadav | 03-Mar-2020
 * Thread running 10 task asynchronously/parallel
 */
public class ThreadBasic_nThreadPool2 {
	public static void main(String[] args) {
		System.out.println("Main thread start");
		for(int i = 0; i < 10; i++) {
			Thread tobj = new Thread(new Task());
			tobj.start();
		}
		
		System.out.println("Main thread end ");
	}
	
	static class Task implements Runnable{
		public void run() {
			System.out.println("Thread name "+Thread.currentThread().getName());
		}
	}
}
/**
Main thread start
Thread name Thread-0
Thread name Thread-1
Thread name Thread-2
Thread name Thread-3
Thread name Thread-4
Thread name Thread-5
Thread name Thread-6
Thread name Thread-7
Thread name Thread-8
Main thread end 
Thread name Thread-9

*/