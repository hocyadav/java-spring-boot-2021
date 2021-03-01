package aug19th;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamFlatMap {
	public static void main(String[] args) throws IOException {
		Stream<String> lines = Files.lines(Paths.get("flatMap.txt"));
		
		//comment 1st 2 line - then it will work
		Stream<String[]> map = lines.map(e -> e.split("\\s+"));//array of stream OR stream of type array :: array type is strin
		Stream<Stream<String>> map2 = lines.map(e -> e.split("\\s+")).map(ary -> Arrays.stream(ary));//convert internal array into stream of that type : like arry -> stream : here string array o stream of string
		Stream<String> flatMap = lines.map(e -> e.split("\\s+")).flatMap(e -> Arrays.stream(e));
		
		//now we can do operation 
		//its easy to do operation on Stream<Type> not Stream<Stream<>> , so its like cancel one stream inside stream and make it one
		flatMap.forEach(System.out::println);
		
		
		//extra
		Stream<String[]> map__ = lines.map(e -> e.split("\\s+"));//map gives output as stream : after spliting it forms array so stream of that is outout
		List<String[]> list__ = lines.map(e -> e.split("\\s+")).collect(Collectors.toList());//convert map to list : here map is stream of array so output is list of array
		
	}
}
