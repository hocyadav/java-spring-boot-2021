package aug14th_reflectionAPI;

import java.lang.reflect.Field;

//2. metadata of fields
public class Test4 {
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Class cl = Guru99VariableMetaData.class;
		
		//get public fields
		Field[] fields = cl.getFields();
		for (Field field : fields) {
			String name = field.getName();
			Class<?> type = field.getType();
			Object fieldValue = field.get(field);
			
			System.out.println(name+" "+type+" "+fieldValue);
		}
		System.out.println("-----");
		
		//all field of given class
		Field[] declaredFields = cl.getDeclaredFields();
		for (Field field : declaredFields) {
			String name = field.getName();
			Class<?> type = field.getType();
			Object object = field.get(field);
			
			System.out.println(name+" "+type+" "+object);
		}
		
	}

}
