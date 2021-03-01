package mar2nd;
/**
 * 
 * @author Hariom Yadav | 02-Mar-2020
 * Running 10 task asynchronously/parallel
 */
public class ThreadExample2 {
	public static void main(String[] args) {
		
		for(int i = 0; i < 10; i++) {
			Thread threadObj = new Thread(new Task());
			threadObj.start();
		}
		System.out.println("Main method completed :");
	}
	
	static class Task implements Runnable{
		public void run() {
			System.out.println("Thread name : "+Thread.currentThread().getName());
		}
	}
}
/**
Thread name : Thread-0
Thread name : Thread-3
Thread name : Thread-2
Thread name : Thread-4
Thread name : Thread-1
Thread name : Thread-5
Thread name : Thread-6
Thread name : Thread-7
Thread name : Thread-8
Main method completed :
Thread name : Thread-9

*/