package july25th.copy3;

public class Test2 {
	public static void main(String[] args) throws InterruptedException {
		MyConnectionPool connectionPool = new MyConnectionPool(4);
		
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						connectionPool.print();
						ConnProducerImpl connObj = connectionPool.checkout();
						connObj.execute("executing my sample task.....");
						connectionPool.close(connObj);
						Thread.sleep(2000);
					} catch (InterruptedException e) { e.printStackTrace();}
				}
			}
		};

		new Thread(runnable).start();
	}
}
