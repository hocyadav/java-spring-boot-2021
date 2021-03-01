package july4th.exception;

public class Amount {
	String curr;
	int amount;
	public Amount(String curr, int amount) {
		this.curr = curr;
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "Amount [curr=" + curr + ", amount=" + amount + "]";
	}
	
}
