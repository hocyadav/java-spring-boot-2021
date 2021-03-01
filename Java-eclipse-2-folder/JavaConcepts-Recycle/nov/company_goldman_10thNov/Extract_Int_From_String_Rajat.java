package company_goldman_10thNov;
/**
 * 
 * @author Hariom Yadav - Nov 10, 2019
 *
 */
public class Extract_Int_From_String_Rajat {
	public static void main(String[] args) {
		String str = "123";
		
		int k = str.charAt(0) - '0';//apply for loop and take one one int from string
		System.out.println(k);
		//similary for others index
		
		System.out.println(str.charAt(1) - '0');//2-0
		System.out.println(str.charAt(1) - '2');//2-2
		
	}
}
/**
1
2
0

*/