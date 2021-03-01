package feb_15th;

import sun.security.util.Length;

public class Op_ab_z_2function_reverseOperation {
	final static int t1 = 3;
	final static int t2 = 5;
	public static void main(String[] args) {
		int a = 11;
		int b = 5;
		int z = fun1(a,  b);
		System.out.println(z);
		fun2(z);
	}


	private static int fun1(int a, int b) {
		int temp1 = a * t1;
		int temp2 = b * t2;
		int decimalCount = String.valueOf(temp1).length();
		//System.out.println(temp1+"."+temp2+" "+decimalCount);
		String fi = String.valueOf(temp1).concat(".").concat(String.valueOf(temp2));
		String ff = decimalCount+fi;
		//System.out.println(ff);
		String ft = ff.replace(".", "");
		//System.out.println(ft);
		
		return Integer.valueOf(ft);
	}
	
	
	
	private static void fun2(int z) {
		String s = String.valueOf(z);
		String s2 = s.substring(0, 1);
		System.out.println(s2);
		
		//for(int i = 0; i < s.length())
	}
}
