package june28thInterruptThreadAfter10min;

public class StopTask_using2Volatile_atomic {
	public static void main(String[] args) throws InterruptedException {
		Task task = new Task();
		Thread t1 = new Thread(task);
		t1.start();
		
		//timeout - 10min
		Thread.sleep(2000);//error ctrl + . -> ctrl + 1
		task.volatileVal = false;
	}
}

class Task implements Runnable{//default same package
	public volatile boolean volatileVal = true;
	@Override
	public void run() {
		while (volatileVal == true) {
			System.out.println("task");
		}
	}
}

