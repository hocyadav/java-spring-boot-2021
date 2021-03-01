package feb_13th;

class CA11 {
	
}
class MyTask31 extends CA11 implements Runnable {
	@Override
	public void run() {
		//body of fun()
		for(int i = 0; i < 5; i++) {
			System.out.println("@@ Printing documen "+i+" - Printer2");
		}
	}
	
}


class MyTask41 implements Runnable {

	@Override
	public void run() {
		for(int i = 0; i < 5; i++) {
			System.out.println("&& Printing documen "+i+" - Printer3");
		}
	}
}
public class ThreadDemo3_impl_Runnable3 {
	public static void main(String[] args) {
		//job 1
		System.out.println("--application started--");//main thread + MyTask3 + MyTask4 are all running parallel ;
		
		//job 2 : as runnable 
		Runnable r = new MyTask31();//1. polymorphic statement
		Thread threadObj = new Thread(r);//2. sending runnable obj into thread class
		threadObj.start();//3. call start
		
		//single line statement : same as above 2 line
		new Thread(new MyTask41()).start();
		
		//job 4
		for(int i = 0; i < 5; i++) {
			System.out.println("^^ Printing documen "+i+" - Printer1");
		}
		
		//job 5
		System.out.println("--Application ended--");
	}
}
