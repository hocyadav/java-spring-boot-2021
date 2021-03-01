package feb_13th;

class MyTask_demon extends Thread{
	
	public void run() {
		//body of fun()
		for(int i = 0; i < 5; i++) {
			System.out.println("@@ Printing documen "+i+" - Printer2");
		}
	}
	
}
//ctr + arrow : move left most or right most
//above + shoft : move + select text
public class ThreadDemo2_extendsThread_demonThread {
	public static void main(String[] args) {//at least 1 thread ==> name is main thread
		//order of execution : main thread : (job1 , job3, job4) + mytask1 thread : (job2)
		//my thread will start same time as main thread
		//total 2 thread running inside one single process
		//they are running  parallel
		
		//job 1
		System.out.println("--application started--");
		
		//job 2
		MyTask_demon obj = new MyTask_demon();
		obj.setDaemon(true); //demon thread, that means start same time as main thread
		obj.start();
		
		//job 3
		for(int i = 0; i < 5; i++) {
			System.out.println("^^ Printing documen "+i+" - Printer1");
		}
		
		//job 4
		System.out.println("--Application ended--");
	}
}
