package june26;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class TestStudent {

	public static List<Student> getAllStudent() {
		Student s = new Student(1,"hari");
		Student s2 = new Student(2,"om");
		Student s3 = new Student(3,"yadav");
		List<Student> ll = new LinkedList<>();
		ll.add(s);
		ll.add(s2);
		ll.add(s3);
		return ll;
	}
	
	static class NewClass implements Comparator<Integer>{
		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}
	}
	public static void main(String[] args) {
		List<Student> allStudent = getAllStudent();
		System.out.println(allStudent);
		
		//m1 with FI full method body and return type
		Comparator<Student> comp_Obj = new Comparator<Student>(){
			@Override
			public int compare(Student o1, Student o2) {
				return o2.getId() - o1.getId();
			}
		};
		
		NewClass class1 = new NewClass(); 
		List<Integer> li = new LinkedList<>();
		Collections.sort(li);
		Collections.sort(li, class1);
		
		Collections.sort(allStudent, comp_Obj);
		
		System.out.println(allStudent);
		
		//m1 with lambda : remove method body and return type 
//		Collections.sort(allStudent, (o1, o2) -> {
//			return o2.getId() - o1.getId();
//		});
//		System.out.println(allStudent);
		
		
		//m1 with lambda
//		Collections.sort(allStudent, (o1, o2) -> o2.getId() - o1.getId());
//		System.out.println(allStudent);
//		
		
	}
}
