package june24.producerConsumer.lockCondition;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
//lock.lock() and lock.unlck() -> with synchronize
//cond1 and cond2 -> with Object type
//await() -> wait(), signalAll -> notifiyAll
public class MyBlockingQueue_WaitNotify<E> {
	Queue<E> qq;
	int max = 16;

	Object cond1 = new Object();
	Object cond2 = new Object();

	public MyBlockingQueue_WaitNotify(int max) {
		this.qq = new LinkedList<E>();
		this.max = max;
	}

	public synchronized void put(E e) throws InterruptedException {
		try {
			if(qq.size() == max) {
				//block the thread
				cond1.wait();//release lock and wait for someone to awake
			}
			qq.add(e);
			cond2.notifyAll();//wake-up cond2 
		} finally {
		}
	}

	public synchronized E take() throws InterruptedException {
		try {
			if(qq.size() == 0) {
				//block the thread
				cond2.wait();
			}
			E item = qq.remove();
			cond1.notifyAll();
			return item;
		} finally {
		}
	}
}
