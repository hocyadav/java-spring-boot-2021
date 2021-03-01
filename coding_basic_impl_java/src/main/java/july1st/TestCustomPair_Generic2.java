package july1st;

public class TestCustomPair_Generic2 {
	public static void main(String[] args) {
		CustomPair_Generic<Integer, String> pr = new CustomPair_Generic<Integer, String>();
		pr.setKey(1);
		pr.setVal("hariom");
		System.out.println(pr);
		System.out.println("key :"+pr.getKey());
		System.out.println("val :"+pr.getVal());
		
		CustomPair_Generic<String, Integer> pr2 = new CustomPair_Generic<String, Integer>();
		pr2.setKey("hariom yadav");
		pr2.setVal(123);
		System.out.println(pr2);
		
	}
}
