package ds_5th_jan;

import java.util.Arrays;

public class Conversion {
	public static void main(String[] args) {
		//1. ---- to string // solve by using String.valueOf() method
		//char to string 
		String s = String.valueOf('a');
		System.out.println("char to string : "+s);
		
		//char array to string
		char[] arr = {'a','b'};
		String s2 = String.valueOf(arr);
		System.out.println("char array to string : "+s2);
		
		//number data like int, float, double, long 
		String s3 = String.valueOf(123);//here input is int
		
		/*-----------------------------------*/
		//String to charArray
		char[] carr = "abcdef".toCharArray();
		System.out.println(carr);//here char array will print as value
		System.out.println("String to charArray : "+carr); // here char array will print as object
		System.out.println("String to charArray : "+String.valueOf(carr));//use String.valueOf()
		
		//Char array to string M1
		String s4 = String.valueOf(carr);//it will print all length : if we want specific lenght from any given index then use below one
		
		//char array to string M2: with given sub array
		String s5 = String.copyValueOf(carr, 1, 3);//index 1 to length 3 // 1st number is index 2nd number is length
		System.out.println(s5);
		
		//String to int
		String s6 = "1234";
		int v = Integer.parseInt(s6);
		System.out.println(v);
		
		//int to string : String.valueOf(int value)
		
		/*-----------------------------------*/
		//Rajat : directly string to char to int
		//char to int logic --> char - '0'
		String str = "123";
		
		int k = str.charAt(0) - '0';//apply for loop and take one one int from string
		System.out.println(k);
		
	}
}
