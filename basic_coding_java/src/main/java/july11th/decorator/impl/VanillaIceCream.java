package july11th.decorator.impl;

import july11th.decorator.IceCream;
import july11th.decorator.IceCreamDecorator;

public class VanillaIceCream extends IceCreamDecorator{

	public VanillaIceCream(IceCream iceCream) {
		super(iceCream);//call decorator constructor - every impl of decorator will call decorator, so we have to pass one implementaion calss object - for that we have created basic ice cream
	}

	@Override
	public double cost() {
		System.out.println("vanilla ice cream..");
		return 1 + super.cost();
	}

}
