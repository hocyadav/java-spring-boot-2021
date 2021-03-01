package feb_13th;

class MyTask {
	public void fun() {
		for(int i = 0; i < 5; i++) {
			System.out.println("@@ Printing documen "+i+" - Printer2");
		}
	}
}

public class ThreadDemo1_ThreadExplain {
	public static void main(String[] args) {//at least 1 thread ==> name is main thread
		//order of execution : job 1 , job2, job 3 (i.e. execution context, i.e. execute sequence wise)
		
		//job 1
		System.out.println("--application started--");
		
		//job 2
		MyTask obj = new MyTask();
		obj.fun();
		
		//job 3
		for(int i = 0; i < 5; i++) {
			System.out.println("^^ Printing documen "+i+" - Printer1");
		}
		
		//job 4
		System.out.println("--Application ended--");
	}
}
