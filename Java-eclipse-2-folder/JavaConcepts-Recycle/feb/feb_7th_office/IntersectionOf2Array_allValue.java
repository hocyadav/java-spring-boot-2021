package feb_7th_office;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntersectionOf2Array_allValue {
	public static void main(String[] args) {
		int[] a1 = {1,2,2,3};
		int[] a2 = {2,2};
		intersection(a1, a2);
	}
	//hmap1 : store a1
	//hmap2 : stote a2
	//list 	: store value (1st find min count for that value)
	private static void intersection(int[] a1, int[] a2) {
		Map<Integer, Integer> hmap1 = fun(a1);
		Map<Integer, Integer> hmap2 = fun(a2);
		System.out.println("hmap1 : "+hmap1);
		System.out.println("hmap2 : "+hmap2);
		
		List<Integer> list = new ArrayList<>();
		for(int a : hmap1.keySet()) {
			if(hmap2.containsKey(a)) {
				for(int i = 0; i < Math.min(hmap1.get(a), hmap2.get(a)); i++) {//find min count and store 
					list.add(a);
				}
			}
		}
		System.out.println("list : "+list);
	}
	
	private static Map<Integer, Integer> fun(int[] a1) {
		Map<Integer, Integer> map = new HashMap<>();
		for(int a : a1) {
			int count = map.getOrDefault(a, 0);
			map.put(a, count + 1);
		}
		return map;
	}
}

/**

hmap1 : {1=1, 2=2, 3=1}
hmap2 : {2=2}
list : [2, 2]

 */
