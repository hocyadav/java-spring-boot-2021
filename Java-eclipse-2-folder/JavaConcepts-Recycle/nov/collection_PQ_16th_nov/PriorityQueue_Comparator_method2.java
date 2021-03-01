package collection_PQ_16th_nov;

import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * 
 * @author Hariom Yadav - Nov 16, 2019
 *
 */
class DummyClass implements Comparator<String>{
	public int compare(String s1, String s2) {//unimplemented method
		return s1.compareTo(s2);
	}
}

public class PriorityQueue_Comparator_method2 {
	public static void main(String[] args) {
		PriorityQueue<String> pq =  new PriorityQueue<String>(new DummyClass());
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
