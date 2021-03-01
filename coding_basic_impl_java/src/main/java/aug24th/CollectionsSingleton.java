package aug24th;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionsSingleton {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 1, 3, 2, 1, 4, 1, 2, 3);
		System.out.println(list);
		//System.out.println(list.remove(1));
		//boolean removeAll = list.removeAll(Collections.singleton(1));
		//System.out.println(removeAll);
		
		Integer a = -1;
		System.out.println(a.compareTo(0) >= 0);
	}

}
