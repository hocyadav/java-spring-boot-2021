package june15th.factory;

public class PhoneFactory {
	public static AbstractPhone getPhone(PhoneType type) {
//		if(type.equals(PhoneType.ANDROID)) {
//			return new Android();
//		}else {
//			return new Iphone();
//		}
		
		switch (type) {
		case IPHONE:
			return new Iphone();
		case ANDROID:
			return new Android();
		default:
			return null;
		}
		
	}
}

//also we can write enum in same class to make single factory class
//enum PhoneType2 {
//	ANDROID,
//	IPHONE
//}
