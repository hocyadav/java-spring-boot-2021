package july16th.condition;

//not working
public class Condition_Test3{
	public static void main(String[] args) throws InterruptedException {
		Producer_Consumer obj = new Producer_Consumer();
		
		Runnable producer = new Runnable() {
			@Override
			public void run() {
				while(true) {
					obj.put();
				}
			}
		};
		
		Runnable consumer = new Runnable() {
			@Override
			public void run() {
				while(true) {
					obj.get();
				}
			}
		};
		
		//new Thread(producer).start();
		new Thread(consumer).start();
	}
	
}
