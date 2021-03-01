package july25th.copy3;

public class Test3_connOBJ {
	public static void main(String[] args) throws InterruptedException {
		//1. 4 worker thread
		//2. get from queue
		//3. task excute
		//4. free - add back to queue
		ConnectionImpl2_connObjIMPL obj = new ConnectionImpl2_connObjIMPL(4);
		
		obj.qqSize();
		ProducerIMPL connObj = obj.getFromBQ();
		connObj.execute("executing my sample task 1st");
		System.out.println("conn id : "+connObj.getConnObjID());
		obj.qqSize();
		
		obj.addToBQ(connObj);

		ProducerIMPL connObj2 = obj.getFromBQ();
		connObj2.execute("executing my sample task 2nd");
		System.out.println("conn id : "+connObj2.getConnObjID());
		obj.qqSize();
		obj.addToBQ(connObj2);

		ProducerIMPL connObj3 = obj.getFromBQ();
		connObj3.execute("executing my sample task 3rd");
		System.out.println("conn id : "+connObj3.getConnObjID());
		obj.qqSize();
		
		ProducerIMPL connObj4 = obj.getFromBQ();
		connObj4.execute("executing my sample task 4th");
		System.out.println("conn id : "+connObj4.getConnObjID());
		obj.qqSize();
		
		ProducerIMPL connObj5 = obj.getFromBQ();
		connObj5.execute("executing my sample task...5th");
		System.out.println("conn id : "+connObj5.getConnObjID());
		obj.qqSize();
		

	}
}
