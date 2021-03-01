package collection_14th_nov;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
/**
 * 
 * @author Hariom Yadav - Nov 14, 2019
 *
 */
class Student implements Comparable<Student>{//Comparable - compareTo(o1) , //Comparator - compare(o1,o2)
	//know something
	int rollNo;
	String name;
	
	//does something
	public Student(int roll, String name) {
		rollNo = roll;
		this.name = name;
	}

	//@Override
	public int compareTo(Student o) {
		if(this.rollNo > o.rollNo)
			return 1;
		else if(this.rollNo < o.rollNo)
			return -1;
		return 0;
	}
	//sort base on name not working : TODO
	/*
	public int compareTo(Student o) {
		if(this.name > o.name)
			return 1;
		else if(this.name < o.name)
			return -1;
		return 0;
	}
	*/
}

public class PriorityQueue_Store_Objects_Comparable {
	public static void main(String[] args) {
		Queue<Student> pq = new PriorityQueue<>();
		//PriorityQueue<Student> pq = new PriorityQueue<>();//same as above
		
		Random robj = new Random();
		
		int i = robj.nextInt(100);//it will give one number between 0 - 100 ?
		Student obj1 = new Student(i, "hari");
		
		int j = robj.nextInt(100);
		Student obj2 = new Student(j, "om");
		
		int k = robj.nextInt(100);
		Student obj3 = new Student(k, "yadav");
		
		//till here no error
		
		pq.add(obj1);
		//till here also no error - coz only one object in PQ So no sorting required
		System.out.println(pq.peek().name);//we can poll that object
		
		pq.add(obj2);//here we will get error - error related to Comparable
		//solution : Student class must implement COmparable interface
		//and then implement Comparable interface uninplemented method - i.e. compare to method - that will compare 2 object and based on that PQ will sort
		//after class name add "implements Comparable<Object>" Object is Student
		pq.add(obj3);
		
		while(!pq.isEmpty()) {
			Student temp = pq.poll();
			System.out.println(temp.rollNo+" "+temp.name+" ");
		}
		
	}
}
/**
 * 
hari
10 yadav 
46 om 
60 hari 
 * 
 */

