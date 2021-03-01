package ds_5th_Nov_Evening;

import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;
/**
 * 
 * @author Hariom Yadav - Nov 5, 2019
 *
 */

public class MaxHeap_using_PriorityQ_Collection {
	public static void main(String[] args) {
		PriorityQueue<Integer> pqObj = new PriorityQueue<>();
		PriorityQueue<Integer> pqObj2 = new PriorityQueue<>(Collections.reverseOrder());
		
		pqObj.add(10); 
		pqObj.add(30);
		pqObj.add(2);
		pqObj.add(10000);
		
		//pqObj.add(121212);
		/*
		 * Iterator it = pqObj.iterator(); while(it.hasNext())
		 * System.out.print(it.next()+" ");
		 */
		while(!pqObj.isEmpty()) {
			System.out.print(pqObj.poll()+" ");
			//pqObj.poll();
		}
		
		/*
		 * System.out.println(""); pqObj.poll(); Iterator it2 = pqObj.iterator();
		 * while(it2.hasNext()) System.out.print(it2.next()+" "); pqObj.remove();
		 * System.out.println("");
		 * 
		 * Iterator it3 = pqObj.iterator(); while(it3.hasNext())
		 * System.out.print(it3.next()+" ");
		 * 
		 * System.out.println(""); //store in array Object[] arr = pqObj.toArray();
		 * for(int i=0; i<arr.length; i++) { System.out.println(arr[i].toString()); }
		 * 
		 */
		
		
		
		
		
		
	}
}
