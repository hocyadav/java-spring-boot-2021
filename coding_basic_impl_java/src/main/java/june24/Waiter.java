package june24;

public class Waiter implements Runnable {
	Message message;

	public Waiter(Message message) {
		this.message = message;
	}

	@Override
	public void run() {
		//sync - start : only call wait
		synchronized (message) {
			System.out.println("waiter Sync - start");
			try {
				System.out.println("message.wait() - release locak and went to sleep");
				message.wait();//release lock + and went to sleep : (to wake up some one call notify on this object i.e. message)
				System.out.println("wakeup by others thread");
			} catch (InterruptedException e) {e.printStackTrace();}
		}//sync - end
		System.out.println("waiter Sync - end");
	}

}
