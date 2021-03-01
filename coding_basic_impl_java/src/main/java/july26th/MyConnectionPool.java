package july26th;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyConnectionPool extends ConnProducerImpl implements ConnConsumer {
	
	int CONNECTION_POOL_SIZE = 8;//default pool size
	BlockingQueue<ConnProducerImpl> connectionPool = null; 

	ReentrantLock lock = new ReentrantLock(true);
	Condition cond1 = lock.newCondition();
	Condition cond2 = lock.newCondition();

	/** create connection pool object and add to Blocking Queue (Thread Pool/Connection Pool)**/
	public MyConnectionPool(int size) {
		CONNECTION_POOL_SIZE = size;
		connectionPool = new ArrayBlockingQueue<ConnProducerImpl>(size);

		for(int i = 0; i < this.CONNECTION_POOL_SIZE; i++) {
			ConnProducerImpl producerIMPL = new ConnProducerImpl();
			producerIMPL.setConnObjID(i);
			connectionPool.add(producerIMPL);
		}
	}

	/** consumer : get single connection object from pool **/
	public ConnProducerImpl checkout() {
		lock.lock();
		ConnProducerImpl connObj = null;
		try {
			while(currentPoolSize() == 0) {
				System.out.println("No connection Obj in Pool --- waiting ---");
				cond1.await();
			}
			connObj = connectionPool.take();
			cond2.signalAll();
			System.out.println("get connection obj from Connection Pool - id	: "+connObj.getConnObjID());
			Thread.sleep(2000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return connObj;
	}

	/** producer : add connection object back to connection pool **/
	public void close(ConnProducerImpl connObj) {
		lock.lock();
		System.out.println("add connection obj back to Connection Pool - id	: "+connObj.getConnObjID());
		try {
			while(currentPoolSize() == this.CONNECTION_POOL_SIZE) {
				cond2.await();
			}
			connectionPool.add(connObj);
			cond1.signalAll();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println();
			lock.unlock();
		}
	}

	/** print connection pool **/
	public void print() {
		Iterator<ConnProducerImpl> it = connectionPool.iterator();
		System.out.print("Connection Pool (size "+currentPoolSize()+") : ");
		while(it.hasNext()) {
			ConnProducerImpl conn = it.next();
			System.out.print(conn.getConnObjID()+" ");
		}
		System.out.println();
	}

	/** get current connection pool size**/
	private int currentPoolSize() {
		return connectionPool.size();
	}
}
