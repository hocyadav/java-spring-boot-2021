package june16th.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Fixed_SingleThreadPool {
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(1);//blocking queue contain as many as task,
		//but one only 1 thread will execute all above task
		//task execution are in sequence
		
		for (int i = 0; i < 100; i++) {
			service.execute(new Task());
		}
	}
}
