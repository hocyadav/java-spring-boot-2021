package july25th.copy;

public class Test3_connOBJ {
	public static void main(String[] args) throws InterruptedException {
		ConnectionImpl2_connObjIMPL obj = new ConnectionImpl2_connObjIMPL(4);
		obj.printBQ();
		
		ProducerIMPL connObj = obj.getFromBQ();
		connObj.execute("my sample task");
//		System.out.println("conn id : "+connObj.getConnObjID());
//		obj.printBQ();

	}
}
