package ds_7th_Nov_Night;

// put(obj key, obj value), putAll(Map map),
//remove(Object key), get(Object key), containsKey(Object key)
//keySet() keys, entrySet() --keys+values

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 * 
 * @author Hariom Yadav - Nov 7, 2019
 *
 */
public class Map_HashMap {
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("a", 1);
		map.put("b", 2);
		
		//putAll()
		
		System.out.println(map);
		
		map.remove("a");
		System.out.println(map);
		
		Set<Map.Entry<String, Integer>>  st = map.entrySet();
		map.put("v", 12);
		System.out.println(st);
		for(Map.Entry<String, Integer> m:st) {
			System.out.print(m.getKey()+" ");
			System.out.print(m.getValue()+" ");
			
		}
		
	}
}
