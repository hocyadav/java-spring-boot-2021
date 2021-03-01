package june24;

public class Notifier implements Runnable{
	Message message;

	public Notifier(Message message) {
		this.message = message;
	}

	@Override
	public void run() {
		synchronized (message) {
			System.out.println("	1 Notifier : sync - start");
			//do operation
			System.out.println("	2 Do some operation..updating message value");
			message.setMsg("Omprakash Yadav");
			message.notify();//make other wait to wake-up
			System.out.println("	3 Make other thread wakeup message.notify() ");
			System.out.println("	4 Notifier : sync - end");
		}
	}

}
