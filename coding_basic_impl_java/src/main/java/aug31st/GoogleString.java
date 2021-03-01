package aug31st;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

public class GoogleString {
	public static void main(String[] args) {
		
		
		Set<String> set = new HashSet<>();
		Set<String> set2 = new HashSet<>();
		final Set<String> set3 = null;
		set.add("hari");
		set.add("om");
		set.add("yadav");
		
		
		System.out.println(set.contains("hari"));
		System.out.println(set2.contains("o"));
		System.out.println(set.isEmpty()); //[1] -> false -> true
		System.out.println(set2.isEmpty());// [] -> true
		System.out.println(Objects.isNull(set3));// [] -> true
		
		Map<String, String> map = new HashMap<>();
		map.put("hari", "om");
		map.put("hari2", "om2");
		System.out.println(map);
		//Set<Entry<String, String>> entrySet = map.entrySet();
		map.entrySet().removeIf(e -> e.getKey().equalsIgnoreCase("hari"));
		System.out.println(map);
		
	}
}
