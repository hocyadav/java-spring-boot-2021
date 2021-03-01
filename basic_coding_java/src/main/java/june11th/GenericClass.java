package june11th;

class Test<T> {//T => Object Type , and this type is non primitive : Integer, Boolean...etc
	T obj;//obj of type T
	Test(T obj) {
		this.obj = obj;
	}
	public T foo() {
		return this.obj;
	}
}

public class GenericClass {
	public static void main(String[] args) {
		Test<Integer> test = new Test(12);
		Integer foo = test.foo();
		System.out.println(foo);
		
		Test<String> test2 = new Test("Hariom");
		String foo2 = test2.foo();
		System.out.println(foo2);
		
		
	}
}
