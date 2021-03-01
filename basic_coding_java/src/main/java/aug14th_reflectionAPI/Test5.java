package aug14th_reflectionAPI;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

//metadata about methods
public class Test5 {
	public static void main(String[] args) {
		Class cl = Guru99MethodMetaData.class;
		//1. get all methods
		Method[] methods = cl.getMethods();
		
		for (Method method : methods) {
			String name = method.getName();
			System.out.println(name);
		}
		
		System.out.println("------");
		//2. get public methods
		Method[] declaredMethods = cl.getDeclaredMethods();
		for (Method method : declaredMethods) {
			String name = method.getName();
			Class<?> returnType = method.getReturnType();
			int modifiers = method.getModifiers();
			String string = Modifier.toString(modifiers);
			
			System.out.println(name+" "+returnType+" "+string);
		}
		
	}

}
