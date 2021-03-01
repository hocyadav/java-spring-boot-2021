package aug13th_exception;

import java.time.Month;

public class Test {
	public static void main(String[] args) {
		BankAccount account = new BankAccount(100);
		account.printBalance();
		account.deposite(10);
		account.printBalance();
		//account.deposite(-10);
		try {
			account.withdraw2(1000);
		} catch (OwnException e) {
			e.printStackTrace();
		}
		account.printBalance();
	}

}
