package june26;

@FunctionalInterface
interface TestFI{
	void test();
}

public class Lambda_ {
	public static void main(String[] args) {
		//impl FI methos :  1. know argument + 2. know return type 
		TestFI hh = () -> {//1. 0 argument
			System.out.println("hello");//2. body
		};
		hh.test();
	}
}
