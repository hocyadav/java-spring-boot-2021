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

public class Stream_ReplaceTag {
	public static void main(String[] args) {
		Map<String,String> variableMap = fillMap();
		Path path = Paths.get("template.txt");
		Stream<String> lines;
		try {
			lines = Files.lines(path,Charset.forName("UTF-8"));
			List<String> replacedLines = lines.map(line -> replaceTag(line,variableMap))
					.collect(Collectors.toList());
			Files.write(path, replacedLines, Charset.forName("UTF-8"));
			lines.close();
			System.out.println("Find and replace done");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Map<String,String> fillMap() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", "John");
		map.put("<<age>>", "25");
		return map;
	}
	private static String replaceTag(String str, Map<String,String> map) {
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (str.contains(entry.getKey())) {
				str = str.replace(entry.getKey(), entry.getValue());
			}
		}
		return str;
	}
}
