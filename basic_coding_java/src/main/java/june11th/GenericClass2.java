package june11th;

class Test2<T, U> {
	T obj1;
	U obj2;
	
	public Test2() {
		super();
	}

	public Test2(T obj1, U obj2) {
		super();
		this.obj1 = obj1;
		this.obj2 = obj2;
	}
	
	public void print() {
		System.out.println(obj1);
		System.out.println(obj2);
	}
}

public class GenericClass2 {
	public static void main(String[] args) {
		Test2<Integer, String> obj = new Test2<Integer, String>(10, "hariom");
		obj.print();
		
		Test2<String, String> obj2 = new Test2<String, String>("hariom", "yadav");
		obj2.print();
		
		
		
		
	}
}
