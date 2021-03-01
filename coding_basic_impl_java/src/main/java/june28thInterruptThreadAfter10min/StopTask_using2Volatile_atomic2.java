package june28thInterruptThreadAfter10min;

import java.util.concurrent.atomic.AtomicBoolean;

public class StopTask_using2Volatile_atomic2 {
	public static void main(String[] args) throws InterruptedException {
		Task2 task = new Task2();
		Thread t1 = new Thread(task);
		t1.start();//4.
		
		//timeout - 10min
		Thread.sleep(2000);//error ctrl + . -> ctrl + 1
		task.atomicBoolean.set(false);//5.
	}
}

class Task2 implements Runnable{//1, create runnable task
	public volatile boolean volatileVal = true;//2. create atomic variable, it is similar to volatile
	public AtomicBoolean atomicBoolean = new AtomicBoolean(true);
	@Override
	public void run() {
		while (atomicBoolean.get() == true) {//3.run task for long time + check on flag value
			System.out.println("task");
		}
	}
}

