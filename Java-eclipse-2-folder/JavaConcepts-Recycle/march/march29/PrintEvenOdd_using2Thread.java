package march29;
/**
 * 
 * @author Hariom Yadav | 30-Mar-2020
 *
 */

//1. thread even calss
	//call even method
//2. thread odd class
	//call odd method
//3. printer class -> 2 methods printEven and printOdd
// https://javaconceptoftheday.com/print-odd-and-even-numbers-by-two-threads-in-java/
class ThreadEven implements Runnable {
	int number;
	PrinterObj printObj;
	
	public ThreadEven(int num, PrinterObj obj) {
		this.number 	= num;
		this.printObj 	= obj;
	}
	
	@Override
	public void run() {
		int i = 2;		//1st even number
		while(i <= this.number) {
			try {
				printObj.printEven(i);//print obj print even method
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
			i += 2;					//next even number
		}
	}
	
}

class ThreadOdd implements Runnable {
	int number;
	PrinterObj printObj;
	public ThreadOdd(int num, PrinterObj obj) {
		this.number 	= num;
		this.printObj 	= obj;
	}
	@Override
	public void run() {
		int i = 1;	//1st odd number
		while(i <= this.number) {
			try {
				printObj.printOdd(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i += 2;
		}
	}
	
}


class PrinterObj {
	boolean flag = false;
	
	public synchronized void printEven(int i) throws InterruptedException {//make this method syncronize : coz both are using same flag
		//1. if flag = false -> that means other method executaion block , wait here
		//2. if flag = true -> execute our task
			// print even number
			// change flag value : so that other method can print/execute there body
			// notify other method to move from 1st if wait block to execution block
		if(flag == false) {
			while(flag == false)//while is similar to if
				wait();//wait, notify and notifiALl method is present in Object class
		}
		if(flag == true) {//else if , not required
			System.out.println(Thread.currentThread().getName()+ i);
			flag = false;
			notify();
		}
	}
	
	public synchronized void printOdd(int i) throws InterruptedException {
		if(flag == true) {
			while(flag == true) wait();			
		}
		if(flag == false) {
			System.out.println(Thread.currentThread().getName()+ i);
			flag = true;
			notify();
		}
	}
}



public class PrintEvenOdd_using2Thread {
	public static void main(String[] args) {
		PrinterObj 	printObj 	= new PrinterObj();
		ThreadOdd 	odd 		= new ThreadOdd(10, printObj);	//both thread even and odd takes common printer obj
		ThreadEven 	even 		= new ThreadEven(10, printObj);
		
		Thread 		t1 			= new Thread(odd);
		Thread 		t2 			= new Thread(even);
		
		t1.setName("odd 	thread - ");//change thread name
		t2.setName("even 	thread - ");
		
		t1.start();
		t2.start();
		
	}
}
/**
odd 	thread - 1
even 	thread - 2
odd 	thread - 3
even 	thread - 4
odd 	thread - 5
even 	thread - 6
odd 	thread - 7
even 	thread - 8
odd 	thread - 9
even 	thread - 10


*/