package ds_PriorityQueue_14th_nov;

import java.util.Collections;
import java.util.PriorityQueue;

public class Priority_Queue {
	public static void main(String[] args) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		PriorityQueue<Integer> pq2 = new PriorityQueue<Integer>((x,y)->y-x);
		
		pq.offer(12);
		pq.add(15);
		pq.add(2);
		pq.add(6);
		pq.add(1);
		
		System.out.println("PQ1 obj : "+pq);//printing PQ data - not in priority order
		
		/*
		while(!pq.isEmpty()) { 
		  System.out.print(pq.poll()+" ");//get data from PQ in priority wise 
		}
		 */
		
		//above while same as below --> peek + remove == poll
		while(!pq.isEmpty()) {
			System.out.print(pq.peek()+" ");//get data from PQ in priority wise
			pq.remove();
		}
		System.out.println();
		
		//same element added in 2nd PQ
		pq2.add(12);
		pq2.add(15);
		pq2.add(2);
		pq2.add(6);
		pq2.add(1);
		
		System.out.println("PQ2 obj : "+pq2);//priority order not preserve??
		
		while(!pq2.isEmpty()) {
			System.out.print(pq2.poll()+" ");//here priority order print output
		}
		System.out.println();
		
	}
}
/**
PQ1 obj : [1, 2, 12, 15, 6]
1 2 6 12 15 
PQ2 obj : [15, 12, 2, 6, 1]
15 12 6 2 1 
*/