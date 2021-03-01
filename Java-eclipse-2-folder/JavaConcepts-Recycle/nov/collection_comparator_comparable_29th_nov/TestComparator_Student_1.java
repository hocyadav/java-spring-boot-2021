package collection_comparator_comparable_29th_nov;

import java.util.Collections;
import java.util.LinkedList;
/**
 * 
 * @author Hariom Yadav | 29-Nov-2019
 *
 */
public class TestComparator_Student_1 {

	public static void main(String[] args) {
		
		Student_1 o1 = new Student_1("hari", 1);
		Student_1 o2 = new Student_1("om", 10);
		Student_1 o3 = new Student_1("yadav", 5);
		Student_1 o4 = new Student_1("ca tech", 20);
		Student_1 o5 = new Student_1("bangalore", 2);
		
		LinkedList<Student_1> listOfStudents = new LinkedList<>();
		listOfStudents.add(o1); listOfStudents.add(o2); listOfStudents.add(o3); 
		listOfStudents.add(o4); listOfStudents.add(o5); 
		

		System.out.print("Intial list : ");
		print(listOfStudents);
		
		//Collections.sort(listOfStudents);// error - this will take only comparable argument class obj
		
		Collections.sort(listOfStudents, new Student_1());//this will take list + new comparator obj
		
		System.out.print("Sorted list : ");
		print(listOfStudents);
		
		
	}
	
	
	public static void print(LinkedList<Student_1> list) {
		for(int i=0; i<list.size(); i++)
			System.out.print(list.get(i).name+"-"+list.get(i).roll+" ");
		System.out.println();
	}
	
	

}

/**

Intial list : hari-1 om-10 yadav-5 ca tech-20 bangalore-2 
Sorted list : hari-1 bangalore-2 yadav-5 om-10 ca tech-20 
 */
