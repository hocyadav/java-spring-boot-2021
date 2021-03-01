package aug2nd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionsSingleton {
	public static void main(String[] args) throws InterruptedException {
		/** singleton map -- only get method are visible**/
		Map<Integer, String> singletonMap = Collections.singletonMap(1, "hariom");
		System.out.println(singletonMap);

		/** singleton list **/
		List<String> list = new ArrayList();

		//		}

		list.add("hari");
		list.add("om");
		list.add("yadav");
		List<List<String>> singletonList = Collections.singletonList(list);
		System.out.println(singletonList);



		Map<Integer, String> map = new HashMap();
		map.put(11, "hari");
		map.put(12, "chandan");
		map.put(13, "yadav");
		Set<Map<Integer, String>> singleton = Collections.singleton(map);
		System.out.println(singleton);
	}
}
/**
 * 
{1=hariom}
[[hari, om, yadav]]
[{11=hari, 12=chandan, 13=yadav}]
 * 
 */
