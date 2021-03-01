package sept20th;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<E> {
	
	Queue<E> queue;
	int max = 16;
	
	ReentrantLock lock = new ReentrantLock(true);
	Condition con1 = lock.newCondition();
	Condition con2 = lock.newCondition();
	
	public MyBlockingQueue(int max) {
		super();
		this.queue = new LinkedList<E>();
		this.max = max;
	}
	
	public void put(E e) throws InterruptedException {
		lock.lock();
		try {
			if(queue.size() == max) {
				con1.await();//block & wait
			}
			queue.add(e);
			con2.signalAll();
		} finally {
			lock.unlock();
		}
	}
	
	
	public E take() throws InterruptedException {
		lock.lock();
		try {
			while(queue.size() == 0) {//if condtion working fine with 1 producer and 1 consumer
				con2.await();//block & wait
			}
			E remove = queue.remove();
			con1.signalAll();
			return remove;
		} finally {
			lock.unlock();
		}
	}
	
	public void printBQ() {
		System.out.println(queue);
	}
	

}
