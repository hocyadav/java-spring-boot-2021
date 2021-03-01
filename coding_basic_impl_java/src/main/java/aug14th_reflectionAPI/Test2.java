package aug14th_reflectionAPI;

import java.lang.reflect.Modifier;

//1. metadata of class
public class Test2 {
	public static void main(String[] args) {
		//1. create class obj
		Class cl = Guru99Base.class;
		
		//2. get class name
		System.out.println(cl.getName());
		
		//3. get super class name - Object here 
		String superClass = cl.getSuperclass().getName();
		System.out.println(superClass);
		
		//4.get all implemented interface name
		Class[] interfaces = cl.getInterfaces();
		for (Class class1 : interfaces) {
			System.out.println(class1.getName());
		}
		
		//5.get modifier name
		int modifiers = cl.getModifiers();//get modifier int value
		String string = Modifier.toString(modifiers);//pass that int value and get modifier name
		System.out.println(string);
	}

}
