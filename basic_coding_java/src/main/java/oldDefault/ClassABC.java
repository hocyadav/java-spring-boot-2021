package oldDefault;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class ClassABC {
	public static void main(String[] args) {
//		String str = "hello world";
//		char c = maxOcc(str);
//		System.out.println(c);
		
		List<Integer> inputNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		//System.out.println(inputNumbers);
		Stream<Integer> stream = inputNumbers.stream();
		stream
		.distinct()
		.peek(new Consumer<Integer>() {

			@Override
			public void accept(Integer t) {
				System.out.println("000");
				System.out.println(t);
			}
		});
		//stream.forEach(t -> System.out.println(t));
		//.peek(t -> System.out.println("peeked :"+t));
	}

	private static char maxOcc(String str) {
		char result = ' ';
		
		Map<Character, Integer> map = new HashMap();//
		
		for(char c : str.toCharArray()) {
			if(!map.containsKey(c)) {
				map.put(c, 1);
			} else
				map.put(c, map.get(c) + 1);
		}
		int t = Integer.MIN_VALUE;
		for(Map.Entry<Character, Integer> it : map.entrySet()) {
			if(it.getValue() > t) {
				t = it.getValue();
				result = it.getKey();
			}
		}
		return result;
	}
}
