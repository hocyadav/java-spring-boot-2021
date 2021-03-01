package july16th.condition;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Producer_Consumer {
	public BlockingQueue<Integer> qq = new ArrayBlockingQueue<Integer>(100);
	Lock lock = new ReentrantLock();
	Condition cond1 = lock.newCondition();
	Condition cond2 = lock.newCondition();
	AtomicInteger atomicInteger = new AtomicInteger(0);

	public void put() {
		lock.lock();
		try {
			Thread.sleep(3000);
			while(qq.size() == 100) {
				cond1.await();
			}
			//addData(atomicInteger.incrementAndGet());
			qq.add(atomicInteger.incrementAndGet());
			System.out.println("Producer : "+qq);
			cond2.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}


	public void get() {
		lock.lock();
		try {
			Thread.sleep(4000);
			while(qq.size() == 0) {
				cond2.await();
			}
			Integer poll = qq.take();
			cond1.signal();
			System.out.println("Consumer : "+qq);
		}catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

//	private void addData(int incrementAndGet) {
//		qq.add(incrementAndGet);
//	}
}
