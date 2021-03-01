package feb_20th;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/**
 * 
 * @author Hariom Yadav | 20-Feb-2020
 *
 */
public class Student_Comparator_OwnLogic {
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
//		Collections.sort(list);//only work if student class has implemented Comparable interface
//		printLL(list);
		
		
		//M1 : i want to sort based on new logic
//		Comparator<Student> cobj = new Comparator<Student>() {
//			public int compare(Student o1, Student o2) {
//				if(o1.id != o2.id) {
//					return o1.id - o2.id;// o2 - o1 gives reverse order
//				}else {
//					return 0;
//				}
//					
//			}
//		};
//		
//		Collections.sort(list, cobj);
//		printLL(list);
//		
		//m2: input variable is objects
		Collections.sort(list, (obj1, obj2)-> {
			if(obj1.id != obj2.id)
				return obj1.id - obj2.id;
			return 0;
		});
		
		//m3
//		Collections.sort(list,(x,y)->{
//			
//            if(x.id != y.id){
//                return y.id - x.id;
//            }
//            return 0;
//            
//        });
		
		printLL(list);
		
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