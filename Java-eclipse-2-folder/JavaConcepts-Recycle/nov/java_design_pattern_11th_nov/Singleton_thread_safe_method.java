package java_design_pattern_11th_nov;
/**
 * 
 * @author Hariom Yadav - Nov 11, 2019
 *
 */
//extension of lazy
public class Singleton_thread_safe_method {
	// create instance 
	private static Singleton_thread_safe_method obj;
	
	//constructor private
	private Singleton_thread_safe_method() {
		// TODO Auto-generated constructor stub
	}
	
	//public static method synchronized
	public static synchronized Singleton_thread_safe_method getObj() {//synhcronized makes only one class can access this method , its like locking
		if(obj == null)
			obj = new Singleton_thread_safe_method();
		return obj;
	}
	
}
