package feb_7th_office;

import java.util.HashSet;
import java.util.Set;

public class IntersectionOf2Array {

	public static void main(String[] args) {
		int[] a1 = {1,2,2,3};
		int[] a2 = {2,2};
		intersection(a1, a2);
	}
	//set1 : store arr1
	//set2 : store arr2 (if val present in set1)
	private static void intersection(int[] a1, int[] a2) {
		Set<Integer> set1 = new HashSet<>();
		for(int a : a1)	set1.add(a);
		
		Set<Integer> set2 = new HashSet<>();
		for(int a : a2) {
			if(set1.contains(a))
				set2.add(a);
		}
		
		System.out.println("intersection : "+set2);//if required convert into array or list
	}
}
