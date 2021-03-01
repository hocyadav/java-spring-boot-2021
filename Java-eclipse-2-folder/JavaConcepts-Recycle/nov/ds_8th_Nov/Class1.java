package ds_8th_Nov;

/**
 * 
 * @author Hariom Yadav - Nov 8, 2019
 *
 */

class ClassA{
	//constructor
	public ClassA() {
		System.out.println("A");
	}
	
	void show(){
		System.out.println("show a");
	}
	 
}

class ClassB extends ClassA{
	//constructor
	public ClassB() {
		super();//hidden its always 1st line inside any constructor - it will call parent constructor
		System.out.println("A");
		
	}
	
	 void show(){
		 super.show();//super is keyword here and in constructor its a method, by this we can call parent class methods
		 System.out.println("show b");
		 super.show();
	 }
}


public class Class1 {
	public static void main(String[] args) {
		ClassB objB = new ClassB();
		objB.show();
		
	}
}
