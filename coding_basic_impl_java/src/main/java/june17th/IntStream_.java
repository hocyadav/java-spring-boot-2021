package june17th;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class IntStream_ {
	static int minele = 0;
	public static void main(String[] args) {
		int[] arr = {11,4,67,77,10,12};
		
		//m0
//		OptionalInt min = IntStream.of(arr).min(); //return optional int
//		System.out.println(min);
//		
//		int asInt = min.getAsInt();
//		System.out.println(asInt);
		
		
		//m1
		System.out.println(minele);
		IntStream.of(arr)
				.min()
				.ifPresent(s -> System.out.println(s));//if present thn print
		
		//m2
		IntStream.of(arr)
				.min()
				.ifPresent(x -> {
					minele = x;//if present then do some operation on that value
				});
		System.out.println(minele);
		
		
		
		//3 distinct smallest number
		
		IntStream.of(arr)
					.distinct()//1. filter distinct 
					.sorted()//2. sort distinct
					.limit(3)//3. after sorting limit to 1st 3
					.forEach(x -> System.out.println(x));//4. print above three element
		
		int sum = IntStream.of(arr).distinct()
						.sorted()
						.limit(3)
						.sum();
		System.out.println(sum);
		
		//create -> process -> consume
		
		IntStream.of(arr)//Create
		.distinct()//process
		.sorted()//process
		.limit(3)//process
		.forEach(x -> System.out.println(x));//consume
		
		//Process methods
		//distinct
		//*sorted
		//limit
		//skip(3) skip 1st 3 element
		//*filter(x -> x%2==0)
		//*map(y -> y*2)
		//boxed() // int -> Integer
		
		
		//Consume methods
		//average, min, max, sum,  count
		//forEach()
		//toArray()
		//boxed().collect(Collectors.toList())
		//anyMatch(x -> x%2==0) //return boolean
		//allMatch(x -> x%2==0)
		
		

		
	}

}
