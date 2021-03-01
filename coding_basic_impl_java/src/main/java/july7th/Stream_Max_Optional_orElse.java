package july7th;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Stream_Max_Optional_orElse {
	public static void main(String[] args) {
		List<Integer> listOfInt = Arrays.asList(1,2,3,5,6,7,8,9);
		
		listOfInt.stream()
			.filter(f -> f%2 == 0)//even
			.forEach(t -> System.out.println(t));
		
		Integer orElse = listOfInt.stream()
								.filter(f -> f%2 == 0)//even
								.max(new Comparator<Integer>() {//output is optional
									@Override
									public int compare(Integer o1, Integer o2) {
										return o1 - o2;
									}
									
								})
								.orElse(-1);//or else take input as optional
								//.forEach(t -> System.out.println(t));//after max we cant use : for each will not work if input is optional
		
		System.out.println("even "+orElse);
	}
}
/**
[1, 2, 3, 5, 6, 7, 8, 9]
[1, 2, 3, 5, 6, 7, 8, 9]

 */
