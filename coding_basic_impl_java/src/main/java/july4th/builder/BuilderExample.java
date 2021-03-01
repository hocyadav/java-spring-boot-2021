package july4th.builder;

public class BuilderExample {
	public static void main(String[] args) {
		Vehicle car = new Vehicle.VehicleBuilder("1500cc", 4).setAirBags(2).build();
		System.out.println(car);
		
		Vehicle bike = new Vehicle.VehicleBuilder("100cc", 2).build();
		System.out.println(bike);
	}
}
