package collection_comparator_comparable_29th_nov;

import java.util.Comparator;
/**
 * 
 * @author Hariom Yadav | 29-Nov-2019
 * Comparator is a functional interface 
 */
public class Student_1 implements Comparator<Student_1>{//comparator required import java.util.Comparator
	String name;
	int roll;
	
	public Student_1() {
		
	}

	public Student_1(String name, int roll) {
		super();
		this.name = name;
		this.roll = roll;
	}

	public int compare(Student_1 o1, Student_1 o2) {
		if(o1.roll > o2.roll) 
			return 1;
		else if(o1.roll < o2.roll) 
			return -1; 
		return 0;
	}
}
