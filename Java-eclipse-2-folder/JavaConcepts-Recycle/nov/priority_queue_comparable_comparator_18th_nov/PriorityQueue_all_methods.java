package priority_queue_comparable_comparator_18th_nov;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
/**
 * 
 * @author Hariom Yadav - Nov 18, 2019
 *
 */
public class PriorityQueue_all_methods {
	public static void main(String[] args) {
		//obj using Q
		Queue<Integer> pq = new PriorityQueue<>();
		PriorityQueue<String> pq2 = new PriorityQueue<>();
		
		pq.add(10); pq.add(2);
		System.out.println("pq : "+pq);
		
		pq2.add("hari"); pq2.add("om"); pq2.add("aa");
		System.out.println("pq2 : "+pq2);
		
		//sort in reverse order
		PriorityQueue<String> pq3 = new PriorityQueue<>(Collections.reverseOrder());
		pq3.add("hari"); pq3.add("om"); pq3.add("aa");
		System.out.println("pq3 : "+pq3);
		
		//store obj of Student1 - comparable
		PriorityQueue<Student1> pq4 = new PriorityQueue<>();
		Student1 s1 = new Student1(5, "hari");
		Student1 s2 = new Student1(10, "om");
		Student1 s3 = new Student1(2, "aa");
		
		pq4.add(s1); pq4.add(s2); pq4.add(s3);
		System.out.println(pq4);//this will sort and print so required Comparable interface impl
		
		//Store obj of Student2 - comparator 
		//comparator obj - with anonymous obj creation : without lambda expression
		Comparator cObj = new Comparator<Student2>() {

			@Override
			public int compare(Student2 o1, Student2 o2) {
				if(o1.roll > o1.roll) return 1;
				else if(o1.roll < o1.roll) return -1;
				return 0;
			}
		};
			
		//comparator with lambda expression
		Comparator<Student2> cObj2 =(Student2 o1, Student2 o2) -> {
				if(o1.roll > o1.roll) return 1;
				else if(o1.roll < o1.roll) return -1;
				return 0;
		};
		
		PriorityQueue<Student2> pq5 = new PriorityQueue<>(2, cObj);//initial capacity with comparator obj
		Student2 s11 = new Student2(5, "hari");
		Student2 s22 = new Student2(10, "om");
		Student2 s33 = new Student2(2, "aa");
		
		pq5.add(s11); pq5.add(s22); pq5.add(s33);
		System.out.println("pq5 : "+pq5);
		System.out.print("PQ5 : ");
		while(!pq5.isEmpty())
			System.out.print(pq5.poll().name+" ");
		System.out.println();
		
		//add collection
		List<Integer> l1 = new ArrayList<>();
		l1.add(12); l1.add(23); l1.add(2); l1.add(5);
		System.out.println("List l1 :"+l1);
		
		Queue<Integer> pq6 = new PriorityQueue<>(l1);
		System.out.println("pq6 : "+pq6);
		
		//add pq
		Queue<Integer> pq7 = new PriorityQueue<>(Collections.reverseOrder());
		pq7.addAll(pq6);
		System.out.println("pq7 : "+pq7);
		
		//convert into array - toArray
		Queue<Integer> pq8 = new PriorityQueue<>();
		pq8.addAll(pq7);
		System.out.println("pq8 : "+pq8);
		Object[] arr = pq8.toArray();
		System.out.println("Object arary : "+arr);
		System.out.print("Array : ");
		for(int i=0; i<arr.length; i++) {//if it is string then 1st type cast and then print
			System.out.print(arr[i]+" ");
		}
		

	}
}
/**
pq : [2, 10]
pq2 : [aa, om, hari]
pq3 : [om, hari, aa]
[priority_queue_comparable_comparator_18th_nov.Student1@7852e922, priority_queue_comparable_comparator_18th_nov.Student1@4e25154f, priority_queue_comparable_comparator_18th_nov.Student1@70dea4e]
pq5 : [priority_queue_comparable_comparator_18th_nov.Student2@65ab7765, priority_queue_comparable_comparator_18th_nov.Student2@1b28cdfa, priority_queue_comparable_comparator_18th_nov.Student2@eed1f14]
PQ5 : hari aa om 
List l1 :[12, 23, 2, 5]
pq6 : [2, 5, 12, 23]
pq7 : [23, 12, 5, 2]
pq8 : [2, 5, 12, 23]
Object arary : [Ljava.lang.Object;@7229724f
Array : 2 5 12 23 
*/