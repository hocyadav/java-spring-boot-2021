package july16th.condition;
//working
public class Condition_Test2{
	public static void main(String[] args) throws InterruptedException {
		Producer_Consumer obj = new Producer_Consumer();
		
		Runnable producer = new Runnable() {
			@Override
			public void run() {
				obj.put();
			}
		};
		new Thread(producer).start();
		new Thread(producer).start();
		new Thread(producer).start();
		
		Runnable consumer = new Runnable() {
			@Override
			public void run() {
				obj.get();
			}
		};
		
		new Thread(consumer).start();
		new Thread(consumer).start();
	}
	
}
