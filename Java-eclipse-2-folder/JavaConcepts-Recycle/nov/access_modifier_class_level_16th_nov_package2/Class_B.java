package access_modifier_class_level_16th_nov_package2;

import access_modifier_class_level_16th_nov_package1.Class_A;//not visible - coz default only visible inside package

public class Class_B {
	public static void main(String[] args) {
		Class_A obj = new Class_A();
		//Problem : class A is default - so only access inside same package 
		//-here we are trying to access from outside other package(folder) so not visible
		
		//solution : make Class_A as public + import that package here
		//TRY : after above solution make class as default but dint remove import package from here 
		//we will get error "Clas_A is not visible"
		
	}
}
