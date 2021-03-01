package feb_20th;
/**
 * 
 * @author Hariom Yadav | 20-Feb-2020
 *
 */
public class Student implements Comparable<Student>{
	String name;
	int id;
	public Student(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}
	
	public int compareTo(Student obj) {
		if(this.id != obj.id) {
			return this.id - obj.id;
		}else 
			return 0;
	}

}
