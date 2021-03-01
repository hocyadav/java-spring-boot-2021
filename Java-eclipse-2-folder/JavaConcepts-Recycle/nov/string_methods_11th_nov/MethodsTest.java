package string_methods_11th_nov;

public class MethodsTest {
	public static void main(String[] args) {
		//method 1 : step by step
		//get char of alphabets (for given i=0,1,.. we are finding any alphabets )
		int i = 2;
		i = i-2;
		char c = (char)('A'+i);
		System.out.println(c);
		//convert above char to string 
		String ss = String.valueOf(c);
		ss = ss+"--";
		System.out.println(ss);
		
		//method 2: in single line - String.valueOf() method takes anything inside and convert into string 
		//char to string
		String s = String.valueOf( (char) ('A'+1) );//always rememebr alphabets are char not string
		//, so we cant get 1st string , 1st we will get char by type caste and 
		//then we will convert char to string by valueOf method
		System.out.println(s);
		
	}
}
