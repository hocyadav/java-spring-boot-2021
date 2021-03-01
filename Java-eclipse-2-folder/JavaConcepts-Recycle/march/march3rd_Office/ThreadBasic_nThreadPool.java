package march3rd_Office;
/**
 * 
 * @author Hariom Yadav | 03-Mar-2020
 *	Thread basic
 */
public class ThreadBasic_nThreadPool {
	public static void main(String[] args) {
		System.out.println("Main thread start");
		
		Thread tobj = new Thread(new Task());//m2  : sending runnable impl class obj
		tobj.start();
		
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
Main thread end 
Thread name Thread-0
*/