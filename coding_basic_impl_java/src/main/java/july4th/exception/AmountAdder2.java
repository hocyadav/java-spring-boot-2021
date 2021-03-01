package july4th.exception;

//1. where problem -> throw Exception
//2. then add throw Exception after method argument
public class AmountAdder2 {
	static Amount addAmount(Amount a1, Amount a2) throws Exception {//exception = checked = calling body take care
		if(!a1.curr.equals(a2.curr)) {
			throw new Exception("Currency different.. check for exception - handle it:  throw or add try catch..");
		}
		return new Amount(a1.curr, a1.amount + a2.amount);
	}
}
