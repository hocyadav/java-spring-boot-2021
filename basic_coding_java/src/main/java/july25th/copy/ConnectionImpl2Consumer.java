package july25th.copy;

public interface ConnectionImpl2Consumer {
	
	//checkout() - consumer : get 1 connection obj from pool
	public Connection1Producer getFromBQ();
}
