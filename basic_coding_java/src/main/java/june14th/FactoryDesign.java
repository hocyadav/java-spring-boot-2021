package june14th;

public class FactoryDesign {
	public Os getInstance(String osName) {
		if(osName.equalsIgnoreCase("android")) {
			return new Android();
		} else if(osName.equalsIgnoreCase("ios")) {
			return new AppleIOS();
		} else {
			return new Android();
		}
	}
}
