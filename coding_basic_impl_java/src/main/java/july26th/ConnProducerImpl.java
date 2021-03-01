package july26th;

public class ConnProducerImpl implements ConnProducer{
	int connObjID;

	//execute() - task operation
	public void execute(String taskToExecute) {
		try {
			System.out.println(taskToExecute);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close(ConnProducerImpl connObj) {}
	
	public int getConnObjID() {
		return connObjID;
	}

	public void setConnObjID(int connObjID) {
		this.connObjID = connObjID;
	}
}
