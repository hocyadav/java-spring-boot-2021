package july4th.exception;

/** https://www.youtube.com/watch?v=wj3UmzeyrvE&t=296s **/
public class CheckedExpTEST_tryCatch {
	public static void main(String[] args) {//check for exception - added throws exception
		AmountAdder2 adder2 = new AmountAdder2();
		Amount a1 = new Amount("INR", 100);
		//Amount a2 = new Amount("INR", 300);
		Amount a2 = new Amount("USD", 300);
		try {
			Amount m = adder2.addAmount(a1, a2);
			System.out.println(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
