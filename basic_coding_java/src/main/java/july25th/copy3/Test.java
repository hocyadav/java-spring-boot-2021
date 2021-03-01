package july25th.copy3;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		MyConnectionPool connectionPool = new MyConnectionPool(4);
		connectionPool.print();
		
		ConnProducerImpl connObj = connectionPool.checkout();
		connObj.execute("executing my sample task.....");
		connectionPool.close(connObj);
		
		connectionPool.print();
		ConnProducerImpl connObj2 = connectionPool.checkout();
		connObj2.execute("executing my sample task.....");
		
		connectionPool.print();
		ConnProducerImpl connObj3 = connectionPool.checkout();
		connObj3.execute("executing my sample task.....");
		connectionPool.print();
		
		ConnProducerImpl connObj4 = connectionPool.checkout();
		connObj4.execute("executing my sample task.....");
		connectionPool.print();
		
		ConnProducerImpl connObj5 = connectionPool.checkout();
		connObj5.execute("executing my sample task.....");
		connectionPool.print();

		ConnProducerImpl connObj6 = connectionPool.checkout();
		connObj6.execute("executing my sample task.....");
		connectionPool.print();
	}
}
