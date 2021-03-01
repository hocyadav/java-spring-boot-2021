package coding_12th_dec_night;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 
 * @author Hariom Yadav | 12-Dec-2019
 * https://www.geeksforgeeks.org/count-pairs-two-linked-lists-whose-sum-equal-given-value/
 */

public class Count_pairs_2_linked_lists_sum_equal_sum_value {
	public static void main(String[] args) {
		Integer arr1[] = {3,1,5,7};
		Integer arr2[] = {8,2,5,3,9};
		
		LinkedList<Integer> head1 = new LinkedList<Integer>(Arrays.asList(arr1));
		LinkedList<Integer> head2 = new LinkedList<>(Arrays.asList(arr2));
		
		int sum = 10;
		int count = countPairsInLL_M1(head1, head2, sum);//2 for loop/iterator
		System.out.println("method1 : "+count);
		
		int count2 = countPairsInLL_M2(head1, head2, sum);//sort : one list natural order, other descending order
		System.out.println("method2 : "+count2);
		
		int count3 = countPairsInLL_M3(head1, head2, sum);//hashing : use HashSet
		System.out.println("method3 : "+count3);
		
		
	}
	
	//hashing
	private static int countPairsInLL_M3(LinkedList<Integer> head1, LinkedList<Integer> head2, int sum) {
		int count = 0;
		
		HashSet<Integer> us = new HashSet<>();
		
		//store list 1
		Iterator<Integer> it1 = head1.iterator();
		while(it1.hasNext())
			us.add(it1.next());
		
		//check for 2nd list : sum - 2nd list data in above hashset
		
		Iterator<Integer> it2 = head2.iterator();
		while(it2.hasNext()) {
			int x = sum - it2.next();
			if(us.contains(x) == true) {//present
				count++;
			}
		}
		return count;
	}

	//m2 : sorting
	private static int countPairsInLL_M2(LinkedList<Integer> head1, LinkedList<Integer> head2, int sum) {
		int count = 0;
		//sort list 1 : natural order : asscending order
		Collections.sort(head1);
		System.out.println("sort list1 natural order : "+head1);
		//sort list 2
		Collections.sort(head2, Collections.reverseOrder());
		System.out.println("sort list 2 reverse order : "+head2);
		
		
		Iterator<Integer> it1 = head1.iterator();
		Iterator<Integer> it2 = head2.iterator();
		
		Integer num1 = it1.hasNext() ? it1.next() : null;
		Integer num2 = it2.hasNext() ? it2.next() : null;
		
		while(num1 != null && num2 != null) {
			
			if(num1 + num2 == sum) {
				num1 = it1.hasNext() ? it1.next() : null;
				num2 = it2.hasNext() ? it2.next() : null;
				count++;
			}else if(num1 + num2 > sum) {
				num2 = it2.hasNext() ? it2.next() : null;
			}else
				num1 = it1.hasNext() ? it1.next() : null;
				
		}
		
		return count;
	}

	//m1 : 2 for loop(iterator )
	private static int countPairsInLL_M1(LinkedList<Integer> head1, LinkedList<Integer> head2, int sum) {
		int count = 0;
		
		Iterator<Integer> it1 = head1.iterator();
		
		while(it1.hasNext()) {
			Integer n = it1.next();

			Iterator<Integer> it2 = head2.iterator();
			
			while(it2.hasNext()) {
				if(n + it2.next() == sum)
					count++;
			}
		}
		return count;
	}
}

/**
method1 : 3
sort list1 natural order : [1, 3, 5, 7]
sort list 2 reverse order : [9, 8, 5, 3, 2]
method2 : 3
method3 : 3

*/