package july25th.copy3;

public interface ConnProducer {
	
	public void execute(String taskToExecute);
	
	//addToBQ()/close() - producer : add connection back to pool
	public void close(ConnProducerImpl connObj);
	
}
