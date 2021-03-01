package june16th.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 100; i++) {
			service.execute(new Task());
		}
	}
}
