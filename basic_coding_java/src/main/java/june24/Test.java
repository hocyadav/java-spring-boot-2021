package june24;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		Message message = new Message();
		message.setMsg("hariom Yadav");
		System.out.println("Initial : "+message.getMsg());
		
		Waiter waiter = new Waiter(message);
		Thread t1 = new Thread(waiter);
		t1.start();
		
		Notifier notifier = new Notifier(message);
		Thread t2 = new Thread(notifier);
		t2.start();//comment this line and see
		
		
		Thread.sleep(2000);//wait for some time to complete both thread, 
							//else this line will execute fast and we can see old value
		System.out.println("Initial : "+message.getMsg());
		
	}
}
