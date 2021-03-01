package ds_3rd_Nov;
/**
 * 
 * @author Hariom Yadav - Nov 3, 2019
 *
 */
interface Super{
	//know somethig
	int i = 0;
	//does something
	static void method(int i) {
		
	}
}

abstract class Base implements Super{
	void method(int i) {
	}
}

class BaseBase extends Base{
	void method(int i) {
		System.out.println(i);
	}
}

public class Interface_Abstarct {
	public static void main(String[] args) {
		BaseBase obj = new BaseBase();
		obj.method(12);
		
	}
}
