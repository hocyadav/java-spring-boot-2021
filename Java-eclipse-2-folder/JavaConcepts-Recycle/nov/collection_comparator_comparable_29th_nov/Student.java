package collection_comparator_comparable_29th_nov;
/**
 * 
 * @author Hariom Yadav | 29-Nov-2019
 *
 */
public class Student implements Comparable<Student>{//comparable not required any import package
	String st_name;
	int rollno;
	
	public int compareTo(Student o) {//sorting logic : Student type objects
		if(this.rollno > o.rollno) return 1;
		else if(this.rollno < o.rollno) return -1;
		return 0;
	}

	public Student(String st_name, int rollno) {
		this.st_name = st_name;
		this.rollno = rollno;
	}
	
}
