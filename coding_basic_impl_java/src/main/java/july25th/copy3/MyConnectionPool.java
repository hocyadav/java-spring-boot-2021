package july25th.copy3;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyConnectionPool extends ConnProducerImpl implements ConnConsumer {
	
	int connectionPoolSize = 4;//default pool size
	BlockingQueue<ConnProducerImpl> connectionPool = null; 

	ReentrantLock lock = new ReentrantLock(true);
	Condition cond1 = lock.newCondition();
	Condition cond2 = lock.newCondition();

	public MyConnectionPool(int connectionPoolSize) {
		this.connectionPoolSize = connectionPoolSize;
		this.connectionPool = new ArrayBlockingQueue<ConnProducerImpl>(connectionPoolSize);

		for(int i = 0; i < this.connectionPoolSize; i++) {
			ConnProducerImpl producerIMPL = new ConnProducerImpl();
			producerIMPL.setConnObjID(i);
			connectionPool.add(producerIMPL);
		}
		//System.out.println("Pool/BQ size : "+qq.size());
	}

	//checkout() - consumer : get 1 connection obj from pool
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

	//close() - producer : add connection back to pool
	public void close(ConnProducerImpl connObj) {
		lock.lock();
		System.out.println("add connection obj back to Connection Pool - id	: "+connObj.getConnObjID());
		try {
			while(currentPoolSize() == this.connectionPoolSize) {
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

	public void print() {
		Iterator<ConnProducerImpl> it = connectionPool.iterator();
		System.out.print("Connection Pool (size "+currentPoolSize()+") : ");
		while(it.hasNext()) {
			ConnProducerImpl conn = it.next();
			System.out.print(conn.getConnObjID()+" ");
		}
		System.out.println();
	}

	private int currentPoolSize() {
		return connectionPool.size();
	}
}
