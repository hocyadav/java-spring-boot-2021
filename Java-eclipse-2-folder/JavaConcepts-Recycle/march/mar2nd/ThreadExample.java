package mar2nd;
/**
 * 
 * @author Hariom Yadav | 02-Mar-2020
 * Running 1 task asynchronously/parallel
 */
public class ThreadExample {
	public static void main(String[] args) {
		Thread threadObj = new Thread(new Task());
		threadObj.start();
		System.out.println("Main method completed :");
	}
	
	static class Task implements Runnable{
		public void run() {
			System.out.println("Thread name : "+Thread.currentThread().getName());
		}
	}
}
/**
Main method completed :
Thread name : Thread-0
*/