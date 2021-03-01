package priority_queue_comparable_comparator_18th_nov;

import java.util.Comparator;
/**
 * 
 * @author Hariom Yadav - Nov 18, 2019
 *
 */
public class Student1 implements Comparable<Student1>{
	int roll;
	String name;
	
	public Student1(int i, String s) {
		roll = i;
		name = s;
	}

	/*
	 * @Override public int compare(Student1 o1, Student1 o2) { if(o1.roll >
	 * o2.roll) return 1; else if(o1.roll < o2.roll) return -1; return 0; }
	 */

	@Override
	public int compareTo(Student1 o2) {
		if(this.roll > o2.roll) return 1;
		else if(this.roll < o2.roll) return -1;
		return 0;
	}
}

