package feb_13th;

public class Cycle_mod_operation {
	public static void main(String[] args) {
		int input = 6;
		int step = 1;
		int[] arr = {1,2,3,4,5};
		fun(arr, input, step);
		
		int input1 = 6;
		int step1 = 2;
		int[] arr1 = {1,2,3,4,5};
		fun(arr1, input1, step1);
		
		
		int input11 = 6;
		int step11 = 5;
		int[] arr11 = {1,2,3,4,5};
		fun(arr11, input11, step11);
		
		
		int input12 = 6;
		int step12 = 6;
		int[] arr12 = {1,2,3,4,5};
		fun(arr12, input12, step12);
	}

	private static void fun(int[] arr, int input, int step) {
		System.out.println("input 	: "+input);
		int loop =  6 % arr.length;
		System.out.println("input after cycle : "+loop);
		
		loop = loop +  step - 1;//IMP
		System.out.println("new start from  : "+loop);
		System.out.println("--------------");
	}
}
/**
input 	: 6
input after cycle : 1
new start from  : 1
--------------
input 	: 6
input after cycle : 1
new start from  : 2
--------------
input 	: 6
input after cycle : 1
new start from  : 5
--------------
input 	: 6
input after cycle : 1
new start from  : 6
--------------

*/