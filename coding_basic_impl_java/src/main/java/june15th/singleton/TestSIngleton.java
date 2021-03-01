package june15th.singleton;

public class TestSIngleton {
	public static void main(String[] args) {
		Singleton singleton = Singleton.getInstance();
		System.out.println(singleton);
		
		Singleton singleton2 = Singleton.getInstance();
		System.out.println(singleton2);
	}
}
