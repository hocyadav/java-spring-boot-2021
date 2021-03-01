package ds_collection_n_LRU_23rd_nov;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueImpMethods {

	public static void main(String[] args) {
		Queue<Integer> pq = new PriorityQueue<>();//natural odering
		//add - add in priority queue
		pq.add(1);
		pq.add(3);
		pq.add(2);
		pq.add(5);
		System.out.println(pq);//natural odering print
		
		while(!pq.isEmpty())
			System.out.print(pq.poll()+" ");
		
		System.out.println();
		
		Queue<Integer> pq2 = new PriorityQueue<>(Collections.reverseOrder());//natural odering
		//add - add in priority queue
		pq2.add(1);
		pq2.add(3);
		pq2.add(2);
		pq2.add(5);
		System.out.println(pq2);//natural odering print
		
		while(!pq2.isEmpty())
			System.out.print(pq2.poll()+" ");
		
		
	}

}
