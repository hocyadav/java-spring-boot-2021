package july26th;

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

/**
 * 
Connection Pool (size 4) : 0 1 2 3 
get connection obj from Connection Pool - id	: 0
executing my sample task.....
add connection obj back to Connection Pool - id	: 0

Connection Pool (size 4) : 1 2 3 0 
get connection obj from Connection Pool - id	: 1
executing my sample task.....
Connection Pool (size 3) : 2 3 0 
get connection obj from Connection Pool - id	: 2
executing my sample task.....
Connection Pool (size 2) : 3 0 
get connection obj from Connection Pool - id	: 3
executing my sample task.....
Connection Pool (size 1) : 0 
get connection obj from Connection Pool - id	: 0
executing my sample task.....
Connection Pool (size 0) : 
No connection Obj in Pool --- waiting ---

 * 
 */
