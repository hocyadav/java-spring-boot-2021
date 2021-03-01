package feb_25th_night;


public class ThreadClassRunnableInterface3AnnomousClassLymbda {
	public static void main(String[] args) throws InterruptedException {
		//since runnable is funcation interface so we can use anonymous class and lymbda expression
		Runnable robj1 	= new Runnable() {
			public void run() {
				for(int i = 0; i < 5; i++) {
					System.out.println("run Hi");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		Runnable robj2 	= () -> {
					for(int i = 0; i < 5; i++) {
						System.out.println("run Hello");
					try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
		};
		
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