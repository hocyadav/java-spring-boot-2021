package march13th;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/**
 * 
 * @author Hariom Yadav | 13-Mar-2020
 *
 */
public class Student1_Test {
	public static void main(String[] args) {
		List<Student1> list = new LinkedList<Student1>();//TODO : functional java coding practice
		list.add(new Student1(1, "hariom"));
		list.add(new Student1(20, "neha"));
		list.add(new Student1(3, "om"));
		list.add(new Student1(4, "chandan"));
		printList(list);
		
		Collections.sort(list, new Student1());//not able to sort since collections class sort method takes input as obj of that class 
								//that have impl comparable interface
		//above error coz it takes comparator obj not comparable - so imp comparator 
		printList(list);
	}

	private static void printList(List<Student1> list) {
		Iterator<Student1> it = list.iterator();
		while(it.hasNext()) {
			Student1 s = it.next();//TODO: how to store value in variable 
			System.out.println(s.rollN+" "+s.name);
		}
		System.out.println();
	}
}
/**
1 hariom
20 neha
3 om
4 chandan

1 hariom
3 om
4 chandan
20 neha

*/