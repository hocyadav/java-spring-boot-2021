package july3rd;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/** https://javapapers.com/core-java/system-out-println/ 
 *  change logic of sysout : now it will store in file intead console
 *  System is class and out is variable of type PrintStream and println is methods inside printStream class
 * **/
public class System_out_println {
	public static void main(String[] args) throws FileNotFoundException {
		FileOutputStream out = new FileOutputStream("log.txt");
		System.setOut(new PrintStream(out));
		
		System.out.println("hariom yadav");
	}
}
