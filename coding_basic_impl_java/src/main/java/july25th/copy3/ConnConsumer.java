package july25th.copy3;

public interface ConnConsumer {
	
	//getFromBQ() / checkout() - consumer : get 1 connection obj from pool
	public ConnProducer checkout();
}
