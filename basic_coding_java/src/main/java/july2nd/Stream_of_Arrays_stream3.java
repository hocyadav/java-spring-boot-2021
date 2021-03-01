package july2nd;

import java.util.stream.Stream;
/**
 * Stream of String
 * @author admin
 *
 */
public class Stream_of_Arrays_stream3 {
	public static void main(String[] args) {
		Stream<String> streamObj = 
				Stream.of("omprakash", "neha", "chandan", "hariom");

		streamObj.forEach(i -> System.out.println(i));
		
		//no type - still work 
		Stream streamObj2 = 
				Stream.of("omprakash", "neha", "chandan", "hariom");
		
		streamObj2.forEach(i -> System.out.println(i));
		
	}
}
