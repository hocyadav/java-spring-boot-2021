package july25th.copy2;

public interface ConnectionImpl2Consumer {
	
	//checkout() - consumer : get 1 connection obj from pool
	public Connection1Producer getFromBQ();
}
