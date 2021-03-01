package collection_comparator_comparable_29th_nov;

import java.util.Collections;
import java.util.LinkedList;
/**
 * 
 * @author Hariom Yadav | 29-Nov-2019
 *
 */
public class TestComparable_Student {

	public static void main(String[] args) {
		Student o1 = new Student("hari", 1);
		Student o2 = new Student("om", 10);
		Student o3 = new Student("yadav", 5);
		Student o4 = new Student("ca tech", 20);
		Student o5 = new Student("bangalore", 2);
		
		LinkedList<Student> listOfStudents = new LinkedList<>();
		listOfStudents.add(o1); listOfStudents.add(o2); listOfStudents.add(o3); 
		listOfStudents.add(o4); listOfStudents.add(o5); 
		
		System.out.print("Intial list : ");
		print(listOfStudents);
		
		Collections.sort(listOfStudents);//sort() method expect that have implemented comparable interface
		
		System.out.print("Sorted list : ");//based on roll num
		print(listOfStudents);
		
	}
	
	public static void print(LinkedList<Student> list) {
		for(int i=0; i<list.size(); i++)
			System.out.print(list.get(i).st_name+"-"+list.get(i).rollno+" ");
		System.out.println();
	}

}
/**
 * 
Intial list : hari-1 om-10 yadav-5 ca tech-20 bangalore-2 
Sorted list : hari-1 bangalore-2 yadav-5 om-10 ca tech-20 
 */
