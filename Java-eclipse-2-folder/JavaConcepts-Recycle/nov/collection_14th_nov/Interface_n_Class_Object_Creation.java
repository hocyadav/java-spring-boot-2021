package collection_14th_nov;
/**
 * 
 * @author Hariom Yadav - Nov 14, 2019
 *
 */
interface Interface1{
	
}

class Class1 implements Interface1{
	
}

public class Interface_n_Class_Object_Creation {

	public static void main(String[] args) {
		//2 ways to create obj of class 1 - both obj are same
		
		Interface1 obj = new Class1();
		
		Class1 obj2 = new Class1();
		
		System.out.println(obj);
		System.out.println(obj2);//see output both are obj of class1
	}

}

/**

collection_14th_nov.Class1@7852e922
collection_14th_nov.Class1@4e25154f

*/