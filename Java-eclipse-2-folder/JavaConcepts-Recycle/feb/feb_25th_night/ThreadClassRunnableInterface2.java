package feb_25th_night;

class Hi2 implements Runnable{

	public void run() {
		for(int i = 0; i < 5; i++) {
			System.out.println("run Hi");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}

class Hello2 implements Runnable{

	public void run() {
		for(int i = 0; i < 5; i++) {
			System.out.println("run Hello");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			
	}
}

public class ThreadClassRunnableInterface2 {
	public static void main(String[] args) throws InterruptedException {
		//3rd : both declaration are same, now class Hi is 
//		Hi2 obj1 	= new Hi2();
//		Hello2 obj2 	= new Hello2();
		Runnable robj1 	= new Hi2();
		Runnable robj2 	= new Hello2();
		
		//2nd
		Thread t1 = new Thread(robj1);//3rd pass runnable obj
		Thread t2 = new Thread(robj2);
		
		//1st
		t1.start();
		t2.start();
		
	}
}
/**
run Hi
run Hello
run Hi
run Hello
run Hi
run Hello
run Hi
run Hello
run Hi
run Hello

*/