package june16th.thread;

public class SimpleThread {
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			Thread t1 = new Thread(new Task());
			t1.start();
		}
	}
}

