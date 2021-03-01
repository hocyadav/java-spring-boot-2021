package july25th;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ConnectionImpl2 implements ConnectionImpl2Consumer{
	int connId;//type int is conn obj that is inside BQ
	
	Connection1Producer connection1Producer;
	ConnectionImpl2Consumer connectionImpl2Consumer;

	int connectionPoolSize = 4;//default pool size
	BlockingQueue<Integer> qq = null; 

	ReentrantLock lock = new ReentrantLock(true);
	Condition cond1 = lock.newCondition();
	Condition cond2 = lock.newCondition();

	//create pool : add 10 object and create pool
	public ConnectionImpl2(int connectionPoolSize) {
		this.connectionPoolSize = connectionPoolSize;
		this.qq = new ArrayBlockingQueue<Integer>(connectionPoolSize);

		IntStream.range(0, connectionPoolSize)
		.forEach(q -> qq.add(q));
		
		this.connection1Producer = this;//since this is class is implementation of these interface
		this.connectionImpl2Consumer = this;
	}

	//checkout() - consumer : get 1 connection obj from pool
	public Connection1Producer getFromBQ() {
		lock.lock();
		try {
			while(qq.size() == 0) {
				cond1.await();
			}
			Integer connObj = qq.take();
			this.connId = connObj;
			cond2.signalAll();
			System.out.println("conn obj :"+connObj);
			//return connObj;
			Thread.sleep(1000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return this.connection1Producer;
	}

	//execute() - task operation
	public void execute(String taskToExecute) {
		System.out.println(taskToExecute);
	}

	//close() - producer : add connection back to pool
	public void addToBQ(Integer connObj) {
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

	public void printBQ() {
		System.out.println(qq);
	}
	
	public int getConnID() {
		return this.connId;
	}
}
