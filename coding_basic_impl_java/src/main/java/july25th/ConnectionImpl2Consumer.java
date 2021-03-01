package july25th;

public interface ConnectionImpl2Consumer extends Connection1Producer{
	
	//checkout() - consumer : get 1 connection obj from pool
	public Connection1Producer getFromBQ();
}
