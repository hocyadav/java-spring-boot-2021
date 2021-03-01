package aug13th_exception;

public class BankAccount {
	int amount = 0;
	
	public BankAccount(int amount) {
		super();
		this.amount = amount;
	}


	public void deposite(int newAmount) {
		if(newAmount < 0) {
			throw new RuntimeException("amount is less than 0");
		}
		this.amount += newAmount;
	}
	
	
	public void withdraw(int withdraw) throws Exception {
		if(amount < withdraw) {
			throw new Exception("amount is greater");
		}
		this.amount -= withdraw;
	}
	
	public void withdraw2(int withdraw) throws OwnException {
		if(amount < withdraw) {
			throw new OwnException("amount is greater");
		}
		this.amount -= withdraw;
	}
	
	public void printBalance() {
		System.out.println(this.amount);
	}
	
	

}
