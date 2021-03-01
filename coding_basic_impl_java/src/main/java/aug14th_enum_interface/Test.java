package aug14th_enum_interface;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		MyInterface interface1;
		interface1 = Enum1.FIRST;
		
		MyInterface interface2;
		interface2 = Enum2.PHONE1;
		
		//similary we can create list of MyInterface and add different values for each array
		List<MyInterface> list = new ArrayList<>();
		list.add(Enum1.FIRST);
		list.add(Enum1.SECOND);
		list.add(Enum2.PHONE1);
		for (MyInterface myInterface : list) {
			System.out.println(myInterface);
			System.out.println(myInterface.getMeth());
		}
		System.out.println();
		//store all enum values in array - imp
		Enum1[] arr = Enum1.values();
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i].getMeth());
		}
		
	}
}
