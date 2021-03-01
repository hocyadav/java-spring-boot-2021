package june26;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class TestFilter {
	public static void main(String[] args) {
		List<String> ll = new LinkedList();
		ll.add("hariom");
		ll.add("om");
		ll.add("yadav");
		ll.add("chandan");
		
		//with Functuonal interfave way - expand way - easy tp understanf
		ll.stream()
		.filter(new Predicate<String>() {
			@Override
			public boolean test(String t) {
				return t.startsWith("o");
			}
		})
		.forEach(new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		});
		
		//with lambda - simple way
		ll.stream()
		.filter(t -> t.startsWith("c"))
		.forEach(t -> System.out.println(t));
		
		
		
		Map<Integer, String> map = new HashMap();
		map.put(1, "hariom");
		map.put(2, "om");
		map.put(3, "chandan");
		map.put(4, "yadav");
	
		//expand way - easy to understanf funtiona l interface
		map.entrySet().stream().filter(new Predicate<Map.Entry<Integer, String>>() {
			@Override
			public boolean test(Entry<Integer, String> t) {
				return t.getKey() == 2;
			}
		}).forEach(new Consumer<Map.Entry<Integer, String>>() {
			@Override
			public void accept(Entry<Integer, String> t) {
				System.out.println(t.getKey());
				System.out.println(t.getValue());
			}
		});
		
		//lambda expression 
		map.entrySet().stream()
					.filter(obj -> obj.getKey() == 3)
					.forEach(obj -> System.out.println(obj.getKey() + " "+ obj.getValue()));
		
	}
}
