package functional_interface_n_lambda_expression_16th_nov;

/**
 * 
 * @author Hariom Yadav - Nov 16, 2019
 *
 */
//requirement for FI is min 1 abstract method , and as many as default method
@FunctionalInterface//1. adda annotation , 2. add one method
interface FunInterface{
	void method();//1 abstract method
	
	//optional default method - as many as we can add
	default void mehod1() {
		System.out.println("default");
	}
}

@FunctionalInterface//1. adda annotation , 2. add one method
interface FunInterface2{
	void method(int i);
}


@FunctionalInterface//1. adda annotation , 2. add one method
interface FunInterface3{
	void method(int i);
}


public class FunctionalInterface_n_Lambda_exp {
	public static void main(String[] args) {
		FunInterface obj = () -> System.out.println("hi");//3.
		obj.method();
		obj.mehod1();
		
		FunInterface2 obj2 = (x) -> System.out.println("hi..."+x);//3.
		obj2.method(2);//0th step take value from argument -> send to (x) then (x) send to body present after "->" line
		
		FunInterface3 obj3 = (x) -> {
			//method body
			System.out.println("obj3 : "+x);//3.
			System.out.println("hello "+x+2);
		};
		obj3.method(2);//0th step take value from argument -> send to (x) then (x) send to body present after "->" line
		
		
		
		
	}
}
/*
hi
default
hi...2
obj3 : 2
hello 22

*/