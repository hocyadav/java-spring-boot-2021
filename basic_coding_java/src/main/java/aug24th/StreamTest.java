package aug24th;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {
	public static void main(String[] args) throws IOException {
		//int -> stream
		IntStream obj1 = IntStream.of(1,2,3,4);//stream obj return type : stream of int
		IntStream obj2 = IntStream.range(1, 5);//stream obj
		
		obj1.filter(i -> i>2).forEach(System.out::println);
		
		Stream<Integer> st1 = Stream.of(1,2,3,4);
		Stream<String> str2 = Stream.of("hariom", "chandan");
		
		//array -> stream
		int[] arr = {1,2,3,4};
		IntStream str3 = Arrays.stream(arr);
		
		int sum = str3.sum();//terminal operator, average,
		
		String[] arr2 = {"hari", "om"};
		Stream<String> str4 = Arrays.stream(arr2);
		
		//collections -> stream
		List<String> list = Arrays.asList("hari", "om", "yadav");
		Stream<String> stream = list.stream();
		
		//file -> stream 
		Stream<String> lines = Files.lines(Paths.get("data2.txt"));
		//do operation -> then close
		lines.close();
		
		//reduce method
		Stream<Integer> str5 = Stream.of(1, 2, 3);
		Integer reduce = str5.reduce(0, new BinaryOperator<Integer>() {
									@Override
									public Integer apply(Integer runnigSumValue, Integer nextStreamValue) {
										return runnigSumValue + nextStreamValue;
									}
								});
		
		//summary statitcs
		Stream<Integer> str6 = Stream.of(1,2,3,4);//not able to call int summarystat , need to conver to int
		
		//stream -> int -> summary stat
		Stream<Integer> str7 = Stream.of(1,2,3,4,5,6);
		IntStream mapToInt = str7.mapToInt(new ToIntFunction<Integer>() {
									@Override
									public int applyAsInt(Integer value) {
										return value;
									}
								});
		System.out.println("-----------");
		//mapToInt.forEach(t -> System.out.println(t));
		IntSummaryStatistics summaryStatistics2 = mapToInt.summaryStatistics();
		System.out.println(summaryStatistics2);
		System.out.println("-----------");
		
		IntSummaryStatistics summaryStatistics = IntStream.of(1,2,3,4).summaryStatistics();
		System.out.println(summaryStatistics.getAverage());
		System.out.println(summaryStatistics);
		
		
		
	}

}
/**
 * 
3
4
-----------
IntSummaryStatistics{count=6, sum=21, min=1, average=3.500000, max=6}
-----------
2.5
IntSummaryStatistics{count=4, sum=10, min=1, average=2.500000, max=4}

 * 
 */
