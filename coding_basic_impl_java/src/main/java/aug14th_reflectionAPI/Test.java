package aug14th_reflectionAPI;

public class Test {
	public static void main(String[] args) throws ClassNotFoundException {
		//Class cls =Class.forName("Test");
		//System.out.println(cls.getName());
		
		
		Test obj = new Test();
		Class class1 = obj.getClass();
		System.out.println(class1.getName());
		
		Class s = Test.class;
		System.out.println(s.getName());
	}

}
