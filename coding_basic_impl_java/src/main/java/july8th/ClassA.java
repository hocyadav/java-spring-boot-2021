package july8th;

public class ClassA implements Inter{
	public void show() {
		System.out.println("hi");
	}
	
	public static void main(String[] args) {
		ClassA obj = new ClassA();
		System.out.println(obj instanceof ClassA);
		System.out.println(obj instanceof Inter);
	}
}
