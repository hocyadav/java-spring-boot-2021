package access_modifier_class_member_16th_nov_package2;

import access_modifier_class_member_16th_nov_package1.Class_A;//not visible - coz default only visible inside package

public class Class_B {
	public static void main(String[] args) {
		Class_A obj = new Class_A();
		
		obj.i = 9;
		//Problem : not visible - coz this class is public (that's why we are able to create obj here) but inside memeber "i" is default(that means i can be visible inside that package)
		//Solution : make "i" as public 
		obj.j =10;//class public , "j" public - so visible outside package 
		
	}
}
