package oldDefault;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test2 {
	public static void main(String[] args) {
		//		Stream<String> of = Stream.of("hari", "om", "yadav");

		Stream.of("hari", "om", "yadav")
		.peek(y -> System.out.println("peek1 - "+y))
		//.filter(t -> t.startsWith(""))
		//.peek(y -> System.out.println("peek - "+y))
		.forEach(t ->System.out.println(t));
		//		
		//		IntStream.of(1,2,33,5,7,99)
		//		.filter(u -> u > 30)
		//		.forEach(y -> System.out.println(y));


		int startIndex = 4;
		int lastIndex = 8;

		IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
		.skip(startIndex - 1)
		.limit(lastIndex - startIndex)
		.forEach(y -> System.out.print(y+" "));

	}
}
