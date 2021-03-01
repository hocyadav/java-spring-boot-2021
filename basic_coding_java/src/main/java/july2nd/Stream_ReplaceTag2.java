package july2nd;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stream_ReplaceTag2 {
	public static void main(String[] args) {
		try {

			Map<String,String> variableMap = fillMap();
			System.out.println("contain what to replace in file \n"+variableMap);//contain what to replace in file
			
			//create path obj - using file location as input
			Path path = Paths.get("template.txt");
			
			//EXTRA
			List<String> readAllLines = Files.readAllLines(path);
			System.out.println("input lines : \n"+readAllLines);
			
			//path obj -> stream of string object using Files.lines static method
			Stream<String> lines = Files.lines(path,Charset.forName("UTF-8"));
			
			//EXTRA : read line and print
			printFileContentUsing_Stream(path);
			
			List<String> replacedLines = lines.map(line -> replaceTag(line,variableMap))//output of replaceTag is String
												.collect(Collectors.toList());//so output will be List<String>
			
			System.out.println("output lines \n"+replacedLines);
			Files.write(path, replacedLines, Charset.forName("UTF-8"));
			lines.close();
			System.out.println("Find and replace done");

		} catch (IOException e) { e.printStackTrace(); }
	}

	private static void printFileContentUsing_Stream(Path path) throws IOException {
		Files.lines(path,Charset.forName("UTF-8"))//this is another stream object - if we use above then we will get error 
				.forEach(p -> System.out.println(p));//on stream we are printing all lines
	}

	public static Map<String,String> fillMap() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", "John");
		map.put("<<age>>", "25");
		return map;
	}
	
	//both input and output is string
	//input string : name ==> output string : hariom
	//input : <<age>> ==> output string : 30
	private static String replaceTag(String str, Map<String,String> map) {//input age : output : get value from map and replace with 30
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (str.contains(entry.getKey())) {
				str = str.replace(entry.getKey(), entry.getValue());
			}
		}
		return str;
	}
}
