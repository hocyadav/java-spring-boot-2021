package july1st;

public class TestCustomPair_Generic {
	public static void main(String[] args) {
		//initial fixed value
		CustomPair_Generic2<Integer, String> pr = new CustomPair_Generic2<Integer, String>(12,"omprakash");
		System.out.println("initail		:"+pr);//print initial value
		pr.setKey(1);
		pr.setVal("hariom");
		System.out.println("new 		:"+pr);//print final value

		
		CustomPair_Generic2<String, Integer> pr2 = new CustomPair_Generic2<String, Integer>("chandan", 1233);
		System.out.println("initail 	:"+pr2);//print initial value
		pr2.setKey("hariom yadav");
		pr2.setVal(123);
		System.out.println("new 		:"+pr2);//print final value
		
	}
}
