package feb_13th;

class CA1 {
	
}
class MyTask3 extends CA1 implements Runnable {
	@Override
	public void run() {
		//body of fun()
		for(int i = 0; i < 5; i++) {
			System.out.println("@@ Printing documen "+i+" - Printer2");
		}
	}
	
}


class MyTask4 implements Runnable {

	@Override
	public void run() {
		for(int i = 0; i < 5; i++) {
			System.out.println("&& Printing documen "+i+" - Printer3");
		}
	}
}
public class ThreadDemo3_impl_Runnable2 {
	public static void main(String[] args) {
		//job 1
		System.out.println("--application started--");//main thread + MyTask3 + MyTask4 are all running parallel ;
		
		//job 2 : as runnable 
		Runnable r = new MyTask3();//1. polymorphic statement
		Thread threadObj = new Thread(r);//2. sending runnable obj into thread class
		threadObj.start();//3. call start
		
		//job 3 : same as above 3 line
		Thread threadObj2 = new Thread(new MyTask4());
		threadObj2.start();
		
		//same as above 2 line
		new Thread(new MyTask4()).start();
		
		//job 4
		for(int i = 0; i < 5; i++) {
			System.out.println("^^ Printing documen "+i+" - Printer1");
		}
		
		//job 5
		System.out.println("--Application ended--");
	}
}
