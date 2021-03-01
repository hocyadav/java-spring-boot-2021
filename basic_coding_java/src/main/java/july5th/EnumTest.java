package july5th;

public class EnumTest {
	public static void main(String[] args) {
		int p = Car.lamborghini.getPrice();
		System.out.println(p);
		
		System.out.println(Car.auto.getPrice());
		System.out.println(Car.auto.getEngine());
		Car.auto.setEngine(123);//update enum value
		System.out.println(Car.auto.getEngine());
	}
}
