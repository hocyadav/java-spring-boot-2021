package june26;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class TestSort {
	public static void main(String[] args) {
		List<Integer> ll = new LinkedList();
		ll.add(1);
		ll.add(11);
		ll.add(13);
		ll.add(10);
		ll.add(199);
		
		//using Collections sort and reverse method
		Collections.sort(ll);
		System.out.println(ll);
		
		Collections.reverse(ll);
		System.out.println(ll);
		
		//using stream
		ll.stream()
			//.sorted() //ascending order
			//.sorted((t1, t2) -> t1 - t2) //same as above
			//.sorted((t1, t2) -> t2 - t1)//reverse of above
			.sorted(Comparator.reverseOrder())//sorted is overloaded method
			.forEach(t -> System.out.println(t));
		
		
		//sort non primitive 
		
		List<Employee> list = new LinkedList();
		list.add(new Employee(176, "Roshan", "IT", 600000));
		list.add(new Employee(388, "Bikash", "CIVIL", 900000));
		list.add(new Employee(470, "Bimal", "DEFENCE", 500000));
		list.add(new Employee(624, "Sourav", "CORE", 400000));
		list.add(new Employee(176, "Prakash", "SOCIAL", 1200000));
		
		//sorting based on salary
		Collections.sort(list, new Comparator<Employee>() {
			@Override
			public int compare(Employee o1, Employee o2) {
				return (int) (o1.getSalary() - o2.getSalary());
			}
		});
		System.out.println(list);
		
		//same as above - lambda form
		Collections.sort(list, (o1, o2) -> (int) (o1.getSalary() - o2.getSalary()));
		
		//stream - same as above 
		list.stream()
		.sorted( (o1, o2) -> (int) (o1.getSalary() - o2.getSalary()))
		.forEach(System.out::println);//method reference
		System.out.println("===================");
		//same as above
		list.stream()
		.sorted(Comparator.comparing(emp->emp.getSalary()))
		.forEach(System.out::println);
		
		System.out.println("===================");
		//same as above - less code line
		list.stream()
		.sorted(Comparator.comparing(Employee::getSalary))//method reference
		.forEach(System.out::println);
		
	}
}
