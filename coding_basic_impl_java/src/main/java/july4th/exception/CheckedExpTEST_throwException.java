package july4th.exception;

public class CheckedExpTEST_throwException {
	public static void main(String[] args) throws Exception {//check for exception - added throws exception
		AmountAdder2 adder2 = new AmountAdder2();
		Amount a1 = new Amount("INR", 100);
		//Amount a2 = new Amount("INR", 300);
		Amount a2 = new Amount("USD", 300);
		Amount m = adder2.addAmount(a1, a2);//method add amount is checked exception - we have to check for exception
		System.out.println(m);
	}
}
