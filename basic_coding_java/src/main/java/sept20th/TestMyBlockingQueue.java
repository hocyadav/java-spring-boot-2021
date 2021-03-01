package sept20th;

public class TestMyBlockingQueue {
	public static void main(String[] args) {
		MyBlockingQueue<Integer> myBlockingQueue = new MyBlockingQueue<Integer>(10);
		
		Runnable producer = new Runnable() {
			public void run() {
				try {
				int i = 0;
				while(true) {
					myBlockingQueue.printBQ();
					myBlockingQueue.put(i++);
						Thread.sleep(5000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		Runnable consumer = new Runnable() {
			public void run() {
				while(true) {
					try {
						myBlockingQueue.printBQ();
						Integer take = myBlockingQueue.take();
						System.out.println(take);
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		new Thread(producer).start();
		new Thread(producer).start();
		
		new Thread(consumer).start();
		new Thread(consumer).start();
	}

}
