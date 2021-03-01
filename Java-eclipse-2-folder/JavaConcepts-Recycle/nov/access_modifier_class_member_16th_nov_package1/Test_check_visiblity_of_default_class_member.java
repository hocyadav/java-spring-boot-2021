package access_modifier_class_member_16th_nov_package1;

public class Test_check_visiblity_of_default_class_member {
	public static void main(String[] args) {
		Class_A obj = new Class_A();
		obj.i = 9; // class memeber i is visible - coz default - and default is visible inside same package
		obj.j = 10;//this is visible anywhere coz of public
	}
}
