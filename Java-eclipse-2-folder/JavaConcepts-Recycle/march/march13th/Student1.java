package march13th;

import java.util.Comparator;
/**
 * 
 * @author Hariom Yadav | 13-Mar-2020
 *
 */
public class Student1 implements Comparator<Student1>{
	int rollN;
	String name;
	
	public Student1() {
	}
	public Student1(int rollN, String name) {
		super();
		this.rollN = rollN;
		this.name = name;
	}
	@Override
	public int compare(Student1 o1, Student1 o2) {
		//return o1.rollN - o2.rollN;//same as below
		if(o1.rollN > o2.rollN) 
			return 1;
		if(o1.rollN < o2.rollN) 
			return -1;
		return 0;
	}
}
