package feb_20th;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Student_Comparable_ClassImpl {
	public static void main(String[] args) {
		Student o1 = new Student("hari", 10);
		Student o2 = new Student("hari", 11);
		Student o3 = new Student("hari", 12);
		Student o4 = new Student("hari", 1);
		
		List<Student> list = new LinkedList();
		list.add(o1);
		list.add(o2);
		list.add(o3);
		list.add(o4);
		
		printLL(list);
		Collections.sort(list);
		printLL(list);
		
		
		//i want to sort based on new logic - working
//		Comparator<Student> cobj = new Comparator<Student>() {
//			public int compare(Student o1, Student o2) {
//				if(o1.id != o2.id) {
//					return o1.id - o2.id;
//				}else {
//					return 0;
//				}
//					
//			}
//		};
//		
//		Collections.sort(list, cobj);
//		printLL(list);
		
		
	}

	private static void printLL(List<Student> list) {
		Iterator<Student> it = list.iterator();
		while(it.hasNext()) {
			Student s = it.next();
			System.out.println(s.id +" "+s.name);
		}
		System.out.println("------");
	}
}
/**
10 hari
11 hari
12 hari
1 hari
------
1 hari
10 hari
11 hari
12 hari
------

*/