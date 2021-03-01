package july25th.copy2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ConnectionImpl2_connObjIMPL extends ProducerIMPL implements ConnectionImpl2Consumer {
	//int connId;//type int is conn obj that is inside BQ
	
	//ProducerIMPL connection1Producer;
	ConnectionImpl2Consumer connectionImpl2Consumer;

	int connectionPoolSize = 4;//default pool size
	BlockingQueue<ProducerIMPL> qq = null; 

	ReentrantLock lock = new ReentrantLock(true);
	Condition cond1 = lock.newCondition();
	Condition cond2 = lock.newCondition();

	//create pool : add 10 object and create pool
	public ConnectionImpl2_connObjIMPL(int connectionPoolSize) {
		this.connectionPoolSize = connectionPoolSize;
		this.qq = new ArrayBlockingQueue<ProducerIMPL>(connectionPoolSize);

		//this.connection1Producer = this;//since this is class is implementation of these interface
		this.connectionImpl2Consumer = this;
		
		for(int i = 0; i < this.connectionPoolSize; i++) {
			ProducerIMPL producerIMPL = new ProducerIMPL();
			producerIMPL.setConnObjID(i);
			qq.add(producerIMPL);
		}
		System.out.println("Pool/BQ size : "+qq.size());
	}

	//checkout() - consumer : get 1 connection obj from pool
	public ProducerIMPL getFromBQ() {
		lock.lock();
		ProducerIMPL connObj = null;
		try {
			while(qq.size() == 0) {
				cond1.await();
			}
			connObj = qq.take();
			cond2.signalAll();
			System.out.println("consuming/return connObj : "+connObj.getConnObjID());
			Thread.sleep(2000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return connObj;
	}

	//close() - producer : add connection back to pool
	public void addToBQ(ProducerIMPL connObj) {
		lock.lock();
		System.out.println("adding back to BQ conn obj : "+connObj);
		try {
			while(qq.size() == this.connectionPoolSize) {
				cond2.await();
			}
			qq.add(connObj);
			cond1.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void printBQ() throws InterruptedException {
		System.out.print("BQ conn OBJ : ");
		BlockingQueue<ProducerIMPL> temp = new ArrayBlockingQueue<ProducerIMPL>(this.connectionPoolSize, true, this.qq);
		for(int i = 0; i < temp.size(); i++) {
			ProducerIMPL take = temp.take();
			System.out.print(take.getConnObjID()+" ");
		}
		System.out.println();
	}
	
	public void qqSize() {
		System.out.println("BQ/Pool size : "+qq.size());
	}
	
}
