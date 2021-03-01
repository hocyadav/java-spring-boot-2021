package june28thInterruptThreadAfter10min;

public class StopTask_using1Thread_interrupt {
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!Thread.currentThread().isInterrupted()) {
					System.out.println("task");
				}
			}
		});
		t1.start();
		
		//timeout - 10min
		Thread.sleep(2000);//error ctrl + . -> ctrl + 1
		
		t1.interrupt();
		
	}
}

//class Task implements Runnable{//default same package
//	@Override
//	public void run() {
//		System.out.println("task");
//	}
//}

