package july25th.copy2;

public class ProducerIMPL implements Connection1Producer{
	int connObjID;

	//execute() - task operation
	public void execute(String taskToExecute) {
		System.out.println(taskToExecute);
	}

	@Override
	public void addToBQ(ProducerIMPL connObj) {

	}

	public int getConnObjID() {
		return connObjID;
	}

	public void setConnObjID(int connObjID) {
		this.connObjID = connObjID;
	}
}
