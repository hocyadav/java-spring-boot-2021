package access_modifier_class_level_16th_nov_package1.test1;

import access_modifier_class_level_16th_nov_package1.Class_A;//coz class A is default and cant visible in another package


public class Hariom {
public static void main(String[] args) {
	Class_A obj = new Class_A();//class A is default so not visible here in another package
}
}
