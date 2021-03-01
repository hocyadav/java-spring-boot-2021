package ds_collection_n_LRU_23rd_nov;

import java.util.Deque;
import java.util.LinkedList;

public class DequeImpMethods {

	public static void main(String[] args) {
		Deque<Integer> dq = new LinkedList<>();
		//add @ tail means end - enqueu - like queue [final list is like array - add will add at end/tail]
		dq.add(1);
		dq.add(2);
		dq.add(3);
		dq.add(4);
		
		System.out.println(dq);
		//remove @ head means first - dequeue like queue - delete 1st in array list
		dq.remove();
		System.out.println(dq);
		
		//remove(obj) - remove given element
		dq.remove(2);
		System.out.println(dq);
		
		//addFirst @ head 
		dq.addFirst(100);
		System.out.println(dq);
		
		dq.removeFirst();
		System.out.println(dq);
		
		dq.removeLast();
		System.out.println(dq);
	}

}
