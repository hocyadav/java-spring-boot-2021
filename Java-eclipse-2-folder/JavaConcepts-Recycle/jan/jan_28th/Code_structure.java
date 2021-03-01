package jan_28th;

public class Code_structure {
	public static void main(String[] args) {
		String 	s1 	= "";
		String 	s2 	= "";
		String 	s3 	= "";
		int 	s4	= 1;
		float 	s5 	= 1;
		fun(s1, s2, s3, s4, s5);
	}
	
	/**
	 * Simple function to see method argument alignment and method body.
	 * @param s1	1st parameter
	 * @param s2	2nd parameter
	 * @param s3	3rd argument
	 * @param s4	4th argument..
	 * @param s5	5th argument..
	 */
	private static void fun(String s1,
							String s2, 
							String s3, 
							int s4, 
							float s5) {
		
		if(s1.equals(s2)) {
			System.out.println("equal..");
		}
		
	}
	
	@SuppressWarnings("unused")
	private static void fun2(String s1, String s2, String s3) {
		System.out.println(s1+s2+s3);
	}
}
