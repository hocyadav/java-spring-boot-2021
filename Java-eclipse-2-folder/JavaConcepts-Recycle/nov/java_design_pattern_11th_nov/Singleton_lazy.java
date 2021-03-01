package java_design_pattern_11th_nov;
/**
 * 
 * @author Hariom Yadav - Nov 11, 2019
 *
 */
//Singleton lazy initialization
public class Singleton_lazy {
	//1. create static instance obj
	private static Singleton_lazy obj;
	//2 make constructor private
	private Singleton_lazy() {}
	//3. public method to send obj of this method
	public static Singleton_lazy getObj() {
		if(obj == null)
			obj = new Singleton_lazy();
		return obj;
	}
}
