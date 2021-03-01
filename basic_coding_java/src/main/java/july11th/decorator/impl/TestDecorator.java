package july11th.decorator.impl;

import july11th.decorator.IceCream;

public class TestDecorator {
	public static void main(String[] args) {
		IceCream basic = new BasicIceCream();
		System.out.println(basic.cost());
		
		IceCream vanilla = new VanillaIceCream(basic);//wrapping on basic ice cream
		System.out.println(vanilla.cost());//1st it will call basic cost() thn add vanilla cost on top of basic
	}
}
