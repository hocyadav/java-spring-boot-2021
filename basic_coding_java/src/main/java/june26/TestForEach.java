package june26;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
/**
 * test on list + map
 * @author hy665678
 *
 */
public class TestForEach {
	public static void main(String[] args) {
		List<String> l1 = new LinkedList<>();
		l1.add("hariom");
		l1.add("chandan");
		l1.add("neha");
		l1.add("om");
		
		l1.stream().forEach(t -> System.out.println(t));
		
		
		Map<Integer, String> map = new HashMap();
		map.put(1, "hari");
		map.put(2, "om");
		map.put(3, "yadav");
		map.put(4, "chandan");
		
		//m1 : using map :  direct map foreach method
		map.forEach(new BiConsumer<Integer, String>() {
			@Override
			public void accept(Integer t, String u) {
				System.out.println("key "+t);
				System.out.println("value "+u);
			}
		});
		
		List<Integer> ll = new LinkedList();
		List<String> l3 = new LinkedList();
		
		map.forEach((k, v) -> {
			//do some operation
			ll.add(k);
			l3.add(v);
		});
		
		System.out.println(ll);
		
		//m2 : using stream
		map.entrySet().stream().forEach(obj -> {
			//do some operation on key or value
			System.out.println(obj.getKey());
			System.out.println(obj.getValue());
		});
		
		
	}
}