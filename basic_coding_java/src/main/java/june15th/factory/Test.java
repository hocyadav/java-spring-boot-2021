package june15th.factory;

public class Test {
	public static void main(String[] args) {
		AbstractPhone phone = PhoneFactory.getPhone(PhoneType.ANDROID);
		System.out.println(phone);
		
		AbstractPhone phone2 = PhoneFactory.getPhone(PhoneType.IPHONE);
		System.out.println(phone2);
		
	}

}
