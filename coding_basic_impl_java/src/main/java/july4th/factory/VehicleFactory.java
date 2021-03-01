package july4th.factory;

//1. create class
//2. create static method + return type Vehicle (super class)
//3. method will take 2 types 1st type string and 2nd fields values that is required to create obj, here only wheel int is enough
public class VehicleFactory {
	public static Vehicle getInstance(String type, int wheel) {
		if(type == "car")
			return new Car(wheel);
		if(type == "bike")
			return new Bike(wheel);
		return null;
	}
}
