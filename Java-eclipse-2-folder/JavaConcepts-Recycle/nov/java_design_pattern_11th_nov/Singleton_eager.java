package java_design_pattern_11th_nov;
/**
 * 
 * @author Hariom Yadav - Nov 11, 2019
 *
 */
//singleton eager initialization
public class Singleton_eager {
	//1. obj / instance make as private and static + create new obj
	private static Singleton_eager obj = new Singleton_eager();
	
	//2. private constructor
	private Singleton_eager() {
		// TODO Auto-generated constructor stub
	}
	
	//3. public method to send obj of this class - since only obj is created and pass to all class
	public static Singleton_eager getObj() {
		return obj;
	}
	
	
	
}
