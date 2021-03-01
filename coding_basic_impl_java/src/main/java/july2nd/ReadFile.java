package july2nd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class ReadFile {
	public static void main(String[] args) throws IOException {
		String pathStr = "file.txt";//path is same as pom.xml
		
		//m1 : read file
		//1. get path object : call paths.get static method
		//2. Files.readAll : static method to get lines in list
		Path paths = Paths.get(pathStr);
		System.out.println(paths);
		
		List<String> readAllLines = Files.readAllLines(paths);
		System.out.println(readAllLines);
		
		System.out.println("====================================");
		//m3 : read file : 
		//1. get file object
		//2. get scanner objext : scanner is like iterator in collection
		//3. while loop like iterator hasNext + next ==> add Line in end
		File file3 = new File(pathStr);
		Scanner scanner = new Scanner(file3);
		while(scanner.hasNextLine()) {
			String s = scanner.nextLine();
			System.out.println(s);
		}
		
		System.out.println("====================================");
		
		//m2 : read file
		//1. get file object
		//2. get filereader object
		//3. get Buffer reader object
		//4. while loop to get value
		File file = new File(pathStr);
		printFileInformation(file);
		
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		System.out.println(bufferedReader.readLine());//print 1st line
		String output = null;
		while((output = bufferedReader.readLine()) != null) {//output store current line pointer 
			System.out.println(output);//print output
		}
		
		System.out.println("====================================");
		File file2 = new File("file23.txt");
		file2.delete();
		file2.createNewFile();
		writeDataInFile(file2);
	}

	private static void writeDataInFile(File file2) throws IOException {
		if(file2.exists()) {
			FileWriter fileWriter = new FileWriter(file2);
			fileWriter.write("d");
			fileWriter.close();
		}else {
			System.out.println("file not present");
		}
	}

	private static void printFileInformation(File file) {
		if(file.exists()) {//check file present or not
			System.out.println("path frm file object: "+file);//print path
			System.out.println("getName() :"+file.getName());
			System.out.println("getAbsolutePath() :"+file.getAbsolutePath());
			System.out.println("canRead() :"+file.canRead());
			System.out.println("canWrite() :"+file.canWrite());
			System.out.println("exists() :"+file.exists());
			System.out.println("size of file in byte :"+file.length());
		}else
			System.out.println("file not present");
	}
}
