package july4th.exception;

public class AmountAdder {
	static Amount addAmount(Amount a1, Amount a2) {
		if(!a1.curr.equals(a2.curr)) {
			throw new RuntimeException("Currency different..runtime unchecked exception..");//Run time = Unchecked Exception = calling body no need to do anything
		}
		return new Amount(a1.curr, a1.amount + a2.amount);
	}
}
