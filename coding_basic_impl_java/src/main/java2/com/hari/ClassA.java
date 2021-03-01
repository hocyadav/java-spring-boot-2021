package com.hari;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.Callable;

class DBTask implements Callable<String>{
	public String call() {
		return "";
	}
}

class ThreadNode{
	int id;
	boolean isAvail = true;

	public ThreadNode(int id) {
		this.id = id;
		this.isAvail = isAvail;
	}

}

class MyThreadPool{
	int minSize;
	int maxSize;
	PriorityQueue<ThreadNode> pq = new PriorityQueue<>();

	public MyThreadPool(int minSize, int max) {
		this.minSize = minSize;
		this.maxSize = max;
	}

	public synchronized ThreadNode get() throws InterruptedException {
		while(pq.size() == maxSize) {
			this.wait();
		}

		if(pq.size() < minSize) {
			ThreadNode node = new ThreadNode(0);
			pq.add(node);
			this.notifyAll();
		}

		return pq.poll();
	}

}

public class ClassA {
	static List<Thread> list = new ArrayList();
	public static void main(String[] args) {
		//ExecutorService service = Executors.newFixedThreadPool(20);
		List<Thread> t = pool(6);



	}

	private static List<Thread> pool(int j) {
		int minSize = 5;
		int maxSize = 10;

		for(int i = 0; i < minSize; i++) {
			Thread thread = new Thread();
			list.add(thread);
		}
		if(j <= 5) return list;
		else {
			for(int i = minSize; i < maxSize; i++) {
				Thread thread = new Thread();
				list.add(thread);
			}
			return list;
		}
	}
}
