package java_design_pattern_11th_nov;
/**
 * 
 * @author Hariom Yadav - Nov 11, 2019
 *
 */
public class Singleton_thread_safe_body {
	// private static obj
	private static Singleton_thread_safe_body obj;
	//private constructor
	private Singleton_thread_safe_body() {
		// TODO Auto-generated constructor stub
	}
	//public method to get obj
	public static Singleton_thread_safe_body getObj() {
		if(obj == null) {
			synchronized (Singleton_thread_safe_method.class) {
				if(obj == null)
					obj = new Singleton_thread_safe_body();
			}			
		}
		return obj;
	}
	
}
