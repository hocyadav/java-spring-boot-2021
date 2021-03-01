package collection_14th_nov;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
/**
 * 
 * @author Hariom Yadav - Nov 14, 2019
 *
 */
class Student_2 implements Comparator<Student_2>{//Comparable - compareTo(o1), Comparator - compare(o1,o2)
	//know something
	int rollNo;
	String name;
	
	public Student_2() {
		// TODO Auto-generated constructor stub
	}
	//does something
	public Student_2(int roll, String name) {
		rollNo = roll;
		this.name = name;
	}

	@Override
	public int compare(Student_2 o1, Student_2 o2) {
		if(o1.rollNo > o2.rollNo) return 1;
		else if(o1.rollNo < o2.rollNo) return -1;
		return 0;
	}
}

public class PriorityQueue_Comparator {
	public static void main(String[] args) {
		PriorityQueue<Student_2> pq = new PriorityQueue<Student_2>(2, new Student_2());
		
		Random robj = new Random();
		
		int i = robj.nextInt(100);//TODO: it will give one number between 0 - 100 ?
		Student_2 obj1 = new Student_2(i, "hari");
		
		int j = robj.nextInt(100);
		Student_2 obj2 = new Student_2(j, "om");
		
		int k = robj.nextInt(100);
		Student_2 obj3 = new Student_2(k, "yadav");
		
		pq.add(obj1);
		System.out.println(pq.peek().name);
		pq.add(obj2);
		pq.add(obj3);
		while(!pq.isEmpty())
			System.out.print(pq.poll().rollNo+" ");
	}
}
/**

hari
12 63 69 
  
 */
