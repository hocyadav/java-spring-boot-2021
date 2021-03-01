package ds_22nd_nov_night_LRU;

import java.util.Deque;
import java.util.LinkedList;
/**
 * 
 * @author Hariom Yadav - Nov 22, 2019
 *
 */
public class DeueueAllMethods {

	public static void main(String[] args) {
		
		//add() -> enqueu, remove() -> dequeu // add and remove follow actual queue operation
		//addFirst, removeFirst // these 2 methods only present coz Deque is doubly linked list and we can add or delete from anywhere
		Deque<Integer> dq = new LinkedList<>();
		
		//add - insert at tail - this method is enqueue operation in Deque - same as Queue
		dq.add(1);
		dq.add(2);
		dq.add(3);
		dq.add(4);
		System.out.println("add(tail) 12, 14 : "+dq);
		
		//push - insert at head
		dq.addFirst(100);//addFirst same as push in dequeu but only remember addFirst
		System.out.println("addFirst(head) 100 : "+dq);
		
		dq.removeFirst();//reverse of addFirst, or use pop()
		System.out.println("removeFirst(head) : "+dq);
		
		dq.removeLast();//same as remove
		System.out.println("remove last : "+dq);
		//remove - dequeu operation in Deque same as Queue
		dq.remove();//or remove last
		System.out.println("remove(tail) : "+dq);
		
	}

}
