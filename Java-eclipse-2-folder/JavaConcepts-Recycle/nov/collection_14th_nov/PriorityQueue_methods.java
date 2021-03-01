package collection_14th_nov;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * 
 * @author Hariom Yadav - Nov 14, 2019
 *
 */
class Std implements Comparator<Std>{
	int roll;
	@Override
	public int compare(Std o1, Std o2) {
		if(o1.roll > o2.roll) return 1;
		else if(o1.roll < o2.roll) return -1;
		return 0;
	}
	
}

public class PriorityQueue_methods {
	public static void main(String[] args) {
		//1. PriorityQueue() 
		//Creates a PriorityQueue with the default initial capacity (11) 
		//that orders its elements according to their natural ordering.
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();//PriorityQueue()
		pq.add(12);	pq.add(1);	pq.add(45);
		System.out.println("PQ - sysout bt not in order : "+pq);
		
		//2. PriorityQueue(Collection<E> c)
		//creates a PriorityQueue containing the elements in the specified collection.
		ArrayList<Integer> ar = new ArrayList<Integer>();
		ar.add(12); ar.add(23); ar.add(34); ar.add(3);
		System.out.println(ar);
		
		PriorityQueue<Integer> pq2 = new PriorityQueue<Integer>(ar);//PriorityQueue(Collection<E> c)
		
		System.out.print("PQ2 : ");
		while(!pq2.isEmpty())
			System.out.print(pq2.poll()+" ");//deleting pq2 data
		System.out.println();
		//all PQ data deleted
		pq2.addAll(ar);//add again
		System.out.println(pq2.size());
		
		//3. PriorityQueue(int initialCapacity)
		PriorityQueue<Integer> pq3 = new PriorityQueue<Integer>(3);//initial capacity 3 this is capacity not size - 
		//but later size will increase dynamically
		System.out.println("size pq3: "+pq3.size());
		
		pq3.add(12); pq3.add(3); pq3.add(34); pq3.add(45);
		pq3.addAll(ar);
		System.out.println("size pq3 after adding ar collection : "+pq3.size());
		System.out.print("PQ3 (some addition + addAll arrayList collection): ");
		while(!pq3.isEmpty())
			System.out.print(pq3.poll()+" ");
		System.out.println();
		
		//4. PriorityQueue(int initialCapacity, Comparator<E> comparator)
		//Creates a PriorityQueue with the specified initial capacity that orders its elements 
		//according to the specified comparator
		
		PriorityQueue<Std> pq4 = new PriorityQueue<Std>(4, new Std());//capacity + class obj that contain comparator interface unimplemented method
		
		
		//5. PriorityQueue(PriorityQueue<E> c)
		//Creates a PriorityQueue containing the elements in the specified priority queue.
		PriorityQueue<Integer> pq5 = new PriorityQueue<Integer>(pq2);
		System.out.print("PQ5 : ");
		while(!pq5.isEmpty())
			System.out.print(pq5.poll()+" ");
		System.out.println();
		
		PriorityQueue<Integer> pq6 = new PriorityQueue<Integer>(Collections.reverseOrder());
		pq6.addAll(pq2);
		//convert PQ to Array (array will store as Object not as Integer , so type cast when required)
		Object[] arrObj = pq6.toArray();
		System.out.print("PQ6 to Array Object to Integer : ");
		for(int i=0; i<arrObj.length ; i++) {
			int y = (int) arrObj[i];
			System.out.print(arrObj[i]+" - ");//print object value - internally call string method
			System.out.print(y+" ");//Object to int value
		}
		System.out.println();
		
		System.out.print("PQ6 (reverse of PQ2)  : ");
		while(!pq6.isEmpty())
			System.out.print(pq6.poll()+" ");
		
		
		//6. PriorityQueue(SortedSet<E> c)
		//Creates a PriorityQueue containing the elements in the specified sorted set.
		
		PriorityQueue<Integer> pq7 = new PriorityQueue<>();
		
	}
}
/**
 * 
 * 
 * 
 PQ - sysout bt not in order : [1, 12, 45]
[12, 23, 34, 3]
PQ2 : 3 12 23 34 
size : 0
size : 8
PQ3 (some addition + addAll arrayList collection): 3 3 12 12 23 34 34 45 
PQ5 : 3 12 23 34 
PQ6 to Array Object to Integer : 34 - 34 23 - 23 12 - 12 3 - 3 
PQ6 (reverse of PQ2)  : 34 23 12 3  
 
 * 
 * 
 */

