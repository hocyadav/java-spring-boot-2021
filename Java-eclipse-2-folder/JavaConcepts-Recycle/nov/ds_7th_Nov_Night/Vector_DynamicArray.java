package ds_7th_Nov_Night;

import java.util.ArrayList;
import java.util.Vector;

/**
 * 
 * @author Hariom Yadav - Nov 7, 2019
 *
 */

//https://www.geeksforgeeks.org/java-util-vector-class-java/
public class Vector_DynamicArray {
	public static void main(String[] args) {
		Vector v = new Vector();
		v.add("a");
		v.add(2);
		System.out.println(v);
		
		v.add(0,"1st");
		System.out.println(v);
		
		ArrayList arr = new ArrayList();
		arr.add("aa");
		arr.add("bb");
		
		System.out.println(arr);
		
		v.addAll(arr);
		System.out.println(v);
		System.out.println("contains : "+v.contains("a"));
		v.remove("a");
		System.out.println(v);
		System.out.println("is empty : "+v.isEmpty());
		v.clear();
		System.out.println(v);
		System.out.println(v.isEmpty());
		
		
	}
}
