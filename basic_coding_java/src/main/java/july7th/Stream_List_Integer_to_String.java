package july7th;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Stream_List_Integer_to_String {
	public static void main(String[] args) {
		List<Integer> listOfInt = Arrays.asList(1,2,3,5,6,7,8,9);
		
		List<String> listOfStr = listOfInt.stream()
			.map(i -> String.valueOf(i))
			.collect(Collectors.toList());
		
		System.out.println(listOfInt);
		System.out.println(listOfStr);
	}
}
/**
[1, 2, 3, 5, 6, 7, 8, 9]
[1, 2, 3, 5, 6, 7, 8, 9]

 */
