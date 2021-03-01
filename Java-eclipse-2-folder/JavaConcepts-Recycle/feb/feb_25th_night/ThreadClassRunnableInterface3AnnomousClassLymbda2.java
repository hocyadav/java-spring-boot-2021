package feb_25th_night;

public class ThreadClassRunnableInterface3AnnomousClassLymbda2 {
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(() -> {
			for(int i = 0; i < 5; i++) {
				System.out.println("run Hi");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(() -> {
			for(int i = 0; i < 5; i++) {
				System.out.println("run Hello");
			try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			});
		
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