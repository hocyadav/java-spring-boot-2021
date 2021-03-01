package june27th;

import java.util.LinkedList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		char c = 65;
		System.out.println(c);
		
		String name = name();
		System.out.println(name);
		@SuppressWarnings("rawtypes")
		List<Integer> list = new LinkedList();
	}
	
	@SuppressWarnings("finally")
	public static String name() {
		try {
			return "t";
		} finally {
			return "f";
		}
	}
	
}