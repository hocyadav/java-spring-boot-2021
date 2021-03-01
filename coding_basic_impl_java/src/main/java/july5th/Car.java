package july5th;

public enum Car {
	lamborghini(900),tata(2), auto(1,2);
	
	private int price;
	private int engine;
	
	Car(int p) {
		price = p;
	}
	Car(int i, int j){
		price = i;
		engine = j;
	}
	
	int getPrice() {
		return price;
	}
	public int getEngine() {
		return engine;
	}
	
	//optional
	public void setEngine(int engine) {
		this.engine = engine;
	} 
}
