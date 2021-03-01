package june24.producerConsumer.lockCondition;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
//generic Queue impl -> add Reentrant lock -> add Queue empty and null condition
//-> create 2 conditions from lock -> use cond.await() its like wait() release and wait for someone to wake-up
//now add line for awake by adding signalAll()

public class MyBlockingQueue_LockCondition<E> {
	Queue<E> qq;
	int max = 16;
	ReentrantLock lock = new ReentrantLock(true);

	Condition cond1 = lock.newCondition();
	Condition cond2 = lock.newCondition();

	public MyBlockingQueue_LockCondition(int max) {
		this.qq = new LinkedList<E>();
		this.max = max;
	}

	public void put(E e) throws InterruptedException {
		lock.lock();
		try {
			if(qq.size() == max) {
				//block the thread
				cond1.await();//release lock and wait for someone to awake
			}
			qq.add(e);
			cond2.signalAll();//wake-up cond2 
		} finally {
			lock.unlock();
		}
	}

	public E take() throws InterruptedException {
		lock.lock();
		try {
			if(qq.size() == 0) {
				//block the thread
				cond2.await();
			}
			E item = qq.remove();
			cond1.signalAll();
			return item;
		} finally {
			lock.unlock();
		}
	}
}
