package july4th.factory;

/** 2. child class **/
public class Car extends Vehicle{
	int wheel;
	
	public Car(int wheel) {
		super();
		this.wheel = wheel;
	}

	@Override
	public int getwWheel() {
		return this.wheel;
	}

	@Override
	public String toString() {
		return "Car [wheel=" + wheel + "]";
	}
	

}
