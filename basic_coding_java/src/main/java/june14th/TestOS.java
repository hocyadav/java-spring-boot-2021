package june14th;

public class TestOS {
	public static void main(String[] args) {
		FactoryDesign factory = new FactoryDesign();
		Os osObj1 = factory.getInstance("android");
		osObj1.osDetails();
		
		
		factory.getInstance("ios").osDetails();//os obj
		factory.getInstance("").osDetails();//default obj
	}
}
