package july4th.factory;

/** 2. child class **/
public class Bike extends Vehicle{
	int wheel;

	public Bike(int wheel) {
		super();
		this.wheel = wheel;
	}
	@Override
	public int getwWheel() {
		return this.wheel;
	}
	@Override
	public String toString() {
		return "Bike [wheel=" + wheel + "]";
	}

}
