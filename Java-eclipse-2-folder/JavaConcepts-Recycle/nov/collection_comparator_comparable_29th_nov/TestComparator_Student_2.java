package collection_comparator_comparable_29th_nov;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
/**
 * 
 * @author Hariom Yadav | 29-Nov-2019
 *
 */
public class TestComparator_Student_2 {

	public static void main(String[] args) {

		Student_2 o1 = new Student_2("hari", 1);
		Student_2 o2 = new Student_2("om", 10);
		Student_2 o3 = new Student_2("yadav", 5);
		Student_2 o4 = new Student_2("ca tech", 20);
		Student_2 o5 = new Student_2("bangalore", 2);
		
		LinkedList<Student_2> listOfStudents = new LinkedList<>();
		listOfStudents.add(o1); listOfStudents.add(o2); listOfStudents.add(o3); 
		listOfStudents.add(o4); listOfStudents.add(o5); 
		

		System.out.print("Intial list : ");
		print(listOfStudents);
		
		//Collections.sort(listOfStudents);// error - this will take only comparable argument class obj
		
		//writing logic for sorting
		Comparator<Student_2> obj123 = new Comparator<Student_2>() {
			@Override
			public int compare(Student_2 o1, Student_2 o2) {
				if(o1.roll > o2.roll) return 1;
				else if(o1.roll < o2.roll) return -1;
				return 0;
			}
		};
		
		//soring based on my logic : logic store in comparator object --> obj
		Collections.sort(listOfStudents, obj123);//this will take list + new comparator obj
		
		System.out.print("Sorted list : ");
		print(listOfStudents);
	}
	
	public static void print(LinkedList<Student_2> list) {
		for(int i=0; i<list.size(); i++)
			System.out.print(list.get(i).name+"-"+list.get(i).roll+" ");
		System.out.println();
	}

}
/**
Intial list : hari-1 om-10 yadav-5 ca tech-20 bangalore-2 
Sorted list : hari-1 bangalore-2 yadav-5 om-10 ca tech-20 

*/