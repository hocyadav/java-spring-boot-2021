package collection_PQ_16th_nov;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
/**
 * 
 * @author Hariom Yadav - Nov 16, 2019
 *
 */
public class PriorityQueue_SortedSet {

	public static void main(String[] args) {
		SortedSet<String> sset = new TreeSet<String>();
		
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(22);
		list.add(2);
		System.out.println(list);
		
		Collections.reverse(list);//it will reverse the list
		System.out.println("reverse : "+list);
		
		Collections.sort(list);//this will sort only primitive data type - https://www.youtube.com/watch?v=oAp4GYprVHM
		//sort will only take that obj of class that has implemented Comparable interface
		//since Integer, String all these primitive wrapper class has implemented this interface and internally written unimplemented class
		//Solution : SO to send our obj , our class 1st need to implement Comparable interface then write logic for sorting in unimplemented method compareTo()
		System.out.println(list);
		//m2 : what if that class is from somewehere else - then we cant update class , 
		//so for that sort() will take 2 input 1st one is same class obj, 2nd one is logic to sort
		
		
		//sort object (1st store in list and then call Collections.sort)
		List<Student> sList = new ArrayList<>();
		Student o1 = new Student(1, "hari");
		Student o2 = new Student(30, "om");
		Student o3 = new Student(4, "yadav");
		
		sList.add(o1); sList.add(o2); sList.add(o3);
		
		Comparator<Student> comparatorObj = new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				return (o1.roll > o2.roll)? 1:-1;
			}
			
		};
		System.out.println("sList obj 		:"+sList);
		Collections.sort(sList, comparatorObj);//sort student object based on roll no
		System.out.println("sList obj sorted	:"+sList);
		
		
	}

}
/**
 * 

[1, 22, 2]
[1, 2, 22]
sList obj 		:[collection_PQ_16th_nov.Student@7852e922, collection_PQ_16th_nov.Student@4e25154f, collection_PQ_16th_nov.Student@70dea4e]
sList obj sorted	:[collection_PQ_16th_nov.Student@7852e922, collection_PQ_16th_nov.Student@70dea4e, collection_PQ_16th_nov.Student@4e25154f]

 */ 
