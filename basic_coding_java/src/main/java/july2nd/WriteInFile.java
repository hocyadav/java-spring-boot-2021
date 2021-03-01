package july2nd;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteInFile {
	public static void main(String[] args) throws IOException {
		//1.create file object
		//2.create print writer obj + print content by using println
		//3. close printwriter obj
		String pathname = "newfile.txt";
		File file = new File(pathname );
		if(!file.exists())
			file.createNewFile();
		
		PrintWriter printWriter = new PrintWriter(file);
		printWriter.println("line1");
		printWriter.println(495452);
		printWriter.close();
	}
}
