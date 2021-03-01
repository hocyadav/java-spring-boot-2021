package july2nd;

import java.util.Arrays;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stream_of_Arrays_stream {
	public static void main(String[] args) {
		//stream of vs arrays.stream
		//both use to get output as stream obj, but for some cases strea.of we have to aain flat map
		//m1 using stream of
		Stream<Integer> streamObj = Stream.of(1,2,3,4,6);
		streamObj.forEach(t -> System.out.println(t));
		
		
		//m2 using arrays.stream : converting array to stream 
		//only fot int, long and double array and output will be IntStream, longStream, doubleStream
		int[] arr = {1,4,3,55,6};
		IntStream stream = Arrays.stream(arr);
		stream.forEach(i -> System.out.println(i));
		
		Stream<int[]> strm = Stream.of(arr);
		//strm.forEach(i -> System.out.println(i));//print object
		//do flatmap to get Intstream obj
		IntStream flatMapToInt = strm.flatMapToInt(Arrays::stream);
		
		//flatMapToInt.forEach(i -> System.out.println(i));//working
		flatMapToInt.forEach(new IntConsumer() {//expand form, for premitive it uses intConsumer
			public void accept(int value) {
				System.out.println(value);
			}
		});
		
	}
}
