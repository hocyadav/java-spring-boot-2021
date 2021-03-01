package july25th.copy;

public abstract class ProducerIMPL implements Connection1Producer{
	int connObjID;

	public int getConnObjID() {
		return connObjID;
	}

	public void setConnObjID(int connObjID) {
		this.connObjID = connObjID;
	}
	
}
