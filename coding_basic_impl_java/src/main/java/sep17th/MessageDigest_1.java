package sep17th;

import java.nio.charset.Charset;
/**
 * 
 * @author hariomyadav
 * https://www.youtube.com/watch?v=dh8ura4rVUM
 * https://www.baeldung.com/java-string-to-byte-array
 */
public class MessageDigest_1 {
	public static void main(String[] args) {
		//1. convert string [to] byte array : solution by using string in-build overloaded method getByte
		//2. convert byte array [to] string : using String  constructor (byte arg)
		
		String input = "hariom";
		
		//m1
		byte[] bytes = input.getBytes();
		printByteArray(bytes);
		//m2
		Charset charsetObj = Charset.defaultCharset();
		System.out.println("charsetObj "+charsetObj);
		byte[] bytes2 = input.getBytes(charsetObj);
		printByteArray(bytes2);
		
		//byte array -> string
		//m1
		String str = new String(bytes);
		System.out.println(str);
		
		//m2
		String str2 = new String(bytes, charsetObj);
		System.out.println(str2);
		
		
	}

	private static void printByteArray(byte[] bytes) {
		for (byte b : bytes) {
			System.out.print(b+" ");
		}
		System.out.println();
	}

}
/**
 * 
104 97 114 105 111 109 
charsetObj UTF-8
104 97 114 105 111 109 
hariom
hariom

 */
