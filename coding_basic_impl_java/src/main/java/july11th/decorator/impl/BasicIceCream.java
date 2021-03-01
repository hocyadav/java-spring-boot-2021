package july11th.decorator.impl;

import july11th.decorator.IceCream;

public class BasicIceCream implements IceCream{

	@Override
	public double cost() {
		System.out.println("basic Ice cream..");
		return 0.5;
	}

}
