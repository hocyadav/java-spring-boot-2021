package collection_14th_nov;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
/**
 * 
 * @author Hariom Yadav - Nov 14, 2019
 *
 */
public class Queue_n_PriorityQ {
	public static void main(String[] args) {
		
		//NOTE: natural ordering So head contain smaller element
		Queue<Integer> q = new PriorityQueue<>();//Queue is Interface and PriorityQueue is class
		//contain bigger element at head position
		//Queue<Integer> q = new PriorityQueue<>(Collections.reverseOrder()); 
		q.add(12);	q.add(1);	q.add(45);

		while(!q.isEmpty())
			System.out.print(q.poll()+" ");
		System.out.println();
		
		
		//NOTE: natural ordering So head contain smaller element
		PriorityQueue<Integer> q2 = new PriorityQueue<>();
		//for bigger element at head position use below anyone
		//PriorityQueue<Integer> q2 = new PriorityQueue<>(Collections.reverseOrder()); //reverse order
		//PriorityQueue<Integer> q2 = new PriorityQueue<>((x,y)->y-x);
		q2.add(12);	q2.add(1);	q2.add(45);

		while(!q2.isEmpty())
			System.out.print(q2.poll()+" ");
				
	}
}
/**
1 12 45 
1 12 45 

*/