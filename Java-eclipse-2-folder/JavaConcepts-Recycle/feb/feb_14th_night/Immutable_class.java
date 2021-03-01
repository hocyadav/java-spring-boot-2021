package feb_14th_night;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Immutable_class {
	
	public static void main(String[] args) {
		
		Employee obj = new Employee("hari", 1234);
		Employee obj2 = new Employee("om", 5678);
		Employee obj3 = new Employee("yadav", 9345);
		
		HashMap<Employee, String> hmap = new HashMap<>();
		hmap.put(obj, "value 1");
		hmap.put(obj2, "value 2");
		hmap.put(obj3, "value 3");
		
		Set<Map.Entry<Employee, String>> set = hmap.entrySet();
		
		for(Map.Entry<Employee, String> s : set) {
			Employee e = s.getKey();
			String name = e.getName();
			int pn = e.getPhoneNumber();
			System.out.println("eobj : "+ e +"	name	: "+ name + "	phone	: " +pn);
		}
		//obj.getName().
		
	
	}
	
}
