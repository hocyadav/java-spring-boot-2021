package july25th.copy;

public class Test2 {
	public static void main(String[] args) {
		ConnectionImpl2 obj = new ConnectionImpl2(4);
		obj.printBQ();
		
		ProducerIMPL connObj = obj.getFromBQ();
		connObj.execute("my sample task");
		System.out.println("conn id : "+connObj.getConnObjID());
		obj.printBQ();

		ProducerIMPL connObj2 = obj.getFromBQ();
		connObj2.execute("my sample task 33");
		System.out.println("conn id : "+connObj2.getConnObjID());
		obj.printBQ();
		
	}
}
