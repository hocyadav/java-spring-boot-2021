package feb_13th;

class CA {
	
}
class MyTask2 extends CA implements Runnable {
	@Override
	public void run() {
		//body of fun()
		for(int i = 0; i < 10; i++) {
			System.out.println("@@ Printing documen "+i+" - Printer2");
		}
	}
	
}

public class ThreadDemo3_impl_Runnable {
	public static void main(String[] args) {//at least 1 thread ==> name is main thread
		//order of execution : main thread : (job1 , job3, job4) + mytask1 thread : (job2) (this is also called as Worker thread/child thread)
		//total 2 thread running inside one single process
		//they are running  parallel : and jvm will give some time to mainThread and some time to myTask thread
		
		//job 1
		System.out.println("--application started--");
		
		//job 2
		//MyTask2 obj = new MyTask2();
		//obj.start();////this is an error : we have to write polymorphic statement
		
		
		//job 2 : as runnable 
		Runnable r = new MyTask2();//1. polymorphic statement
		Thread threadObj = new Thread(r);//2. sending runnable obj into thread class
		threadObj.start();//3. call start
		
		
		//job 3
		for(int i = 0; i < 10; i++) {
			System.out.println("^^ Printing documen "+i+" - Printer1");
		}
		
		//job 4
		System.out.println("--Application ended--");
	}
}
