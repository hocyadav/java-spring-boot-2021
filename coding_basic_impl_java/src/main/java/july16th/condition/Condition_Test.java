package july16th.condition;

public class Condition_Test{
	public static void main(String[] args) throws InterruptedException {
		Producer_Consumer obj = new Producer_Consumer();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				obj.put();
			}
		});
		
		t1.start();
		//t1.start();
		Thread.sleep(2000);
		System.out.println(obj.qq);
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				obj.get();
			}
		});
		t2.start();
	}
	
}
