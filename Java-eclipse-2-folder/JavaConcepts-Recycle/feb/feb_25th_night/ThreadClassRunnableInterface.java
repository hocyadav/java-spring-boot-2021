package feb_25th_night;

class Hi extends Thread{
//	public void fun() {
//		for(int i = 0; i < 5; i++)
//			System.out.println("Hi");
//	}
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

class Hello extends Thread{
//	public void fun() {
//		for(int i = 0; i < 5; i++)
//			System.out.println("Hello");
//	}
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

public class ThreadClassRunnableInterface {
	public static void main(String[] args) throws InterruptedException {
		Hi obj1 	= new Hi();
		Hello obj2 	= new Hello();
		//before thread : execute sequence wise
//		obj1.fun();
//		obj2.fun();
		
		//after thread : execute parallel + write fun body in run method
//		obj1.start();
//		obj2.start();
		
		//above one go to cpu scheduler same time and print any one any order 
		//here schedure knows thread 1 comes 1st so print this request in 1st priority
		obj1.start();
		Thread.sleep(10);//this makes cpu scheduler to give thread 1 as high priority
		obj2.start();
		
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