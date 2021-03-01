package july11th.decorator;

public class IceCreamDecorator implements IceCream{
	private IceCream iceCream;//1.
	
	public IceCreamDecorator(IceCream iceCream) {//2. pass 1
		super();
		this.iceCream = iceCream;
	}

	@Override
	public double cost() {
		return this.iceCream.cost();//2. use 1 to call its method - dont do implementaion here
	}
}
