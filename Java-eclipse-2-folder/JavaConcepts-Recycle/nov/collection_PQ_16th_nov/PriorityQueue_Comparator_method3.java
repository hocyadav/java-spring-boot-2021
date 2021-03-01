package collection_PQ_16th_nov;

import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * 
 * @author Hariom Yadav - Nov 16, 2019
 *
 */


public class PriorityQueue_Comparator_method3 {
	public static void main(String[] args) {
		
		Comparator<String> comparator_Logic = new Comparator<String>() {
			public int compare(String s1, String s2) {
				return s1.compareTo(s2);
			}
		};
		
		PriorityQueue<String> pq =  new PriorityQueue<String>(comparator_Logic);
		pq.add("om"); pq.add("o"); pq.add("yadav");
		System.out.println(pq);
		
		Comparator cobj = pq.comparator();
		System.out.println(cobj);//null means comparator will follow natural ordering sorting 
		//here comparator is type of DummyClass - means Dummy class has implemented compare method
		
	}
}
/**
 * 

 [o, om, yadav]
collection_PQ_16th_nov.DummyClass@7852e922
 */
