package july4th.builder;

/** builder design patter impl **/
public class Vehicle {
	//required parameter
	private String engine;
	private int wheel;
	//optional parameter
	private int airbags;

	public String getEngine() {
		return engine;
	}
	public int getWheel() {
		return wheel;
	}
	public int getAirbags() {
		return airbags;
	}
	private Vehicle(VehicleBuilder builder) {
		this.engine = builder.engine;
		this.wheel = builder.wheel;
		this.airbags= builder.airbags;
	}

	public static class VehicleBuilder{//1. public static constructor + all fields
		//required parameter
		private String engine;
		private int wheel;

		//optional parameter
		private int airbags;

		//public constructor with required parameter
		public VehicleBuilder(String engine, int wheel) {//2. public constructor with required argument
			this.engine = engine;
			this.wheel = wheel;
		}
		//optional parameter inside setter + return type VehicleBuilder
		public VehicleBuilder setAirBags(int airbags) {//3. setter with optional parameter + return this VehicleBuilder
			this.airbags = airbags;
			return this;
		}
		//return Vehicle
		public Vehicle build() {//4. call outer constructor inside build and send this arument i.e. vehicle builder class that contain all values
			return new Vehicle(this);
		}
	}


	@Override
	public String toString() {
		return "Vehicle [engine=" + engine + ", wheel=" + wheel + ", airbags=" + airbags + "]";
	}
}
