package ds_n_LRU_impl_21st_nov;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
/**
 * 
 * @author Hariom Yadav - Nov 21, 2019
 *
 */
public class Dequeu_java_collection_methods {

	public static void main(String[] args) {
		Deque<Integer> dq = new LinkedList<>();
		
		dq.add(12);dq.add(13);
		System.out.println("add		: "+dq);
		dq.addFirst(10);
		System.out.println("addFirst 10	: "+dq);
		dq.addLast(15);
		System.out.println("addLast 15	: "+dq);
		
		
		System.out.println("Iterator");
		
		Iterator it = dq.iterator();
		while(it.hasNext())
			System.out.println(it.next());
		
		System.out.println("descendingIterator");
		
		Iterator it2 = dq.descendingIterator();
		while(it2.hasNext())
			System.out.println(it2.next());
		
		System.out.println("peek : "+dq.peek());//print
		System.out.println(dq);
		
		System.out.println("poll : "+dq.poll());//print + delete
		System.out.println(dq);
		
		System.out.println("contains 12 : "+dq.contains(12));
		
		dq.removeFirst();
		System.out.println("removeFirst : "+dq);
		
		dq.removeLast();
		System.out.println("removeLast : "+dq);
		
	}

}
