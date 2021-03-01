package july2nd;

import java.util.stream.Stream;
/**
 * Stream of object
 * @author admin
 *
 */
public class Stream_of_Arrays_stream2 {
	public static void main(String[] args) {
		Stream<Student> streamObj = 
				Stream.of(new Student(123,"hari"), 
						new Student(345, "om"));

		streamObj.forEach(i -> System.out.println(i));
		//streamObj.filter(f -> f.getRoll() == 345).forEach(f -> System.out.println(f));
		
	}
}
