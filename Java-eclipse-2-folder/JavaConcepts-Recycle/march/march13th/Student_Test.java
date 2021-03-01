package march13th;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
//TODO : comparator functional interface internal method : default + others 
public class Student_Test {
	public static void main(String[] args) {
		List<Student> list = new LinkedList<Student>();//TODO : functional java coding practice
		list.add(new Student(1, "hariom"));
		list.add(new Student(2, "neha"));
		list.add(new Student(3, "om"));
		list.add(new Student(4, "chandan"));
		printList(list);
		
		Collections.sort(list);//not able to sort since collections class sort method takes input as obj of that class 
								//that have impl comparable interface
		
	}

	private static void printList(List<Student> list) {
		Iterator<Student> it = list.iterator();
		while(it.hasNext()) {
			Student s = it.next();//TODO: how to store value in variable 
			System.out.println(s.rollN+" "+s.name);
		}
	}
	
	
}
