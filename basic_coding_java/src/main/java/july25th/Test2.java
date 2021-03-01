package july25th;

public class Test2 {
	public static void main(String[] args) {
		ConnectionImpl2 obj = new ConnectionImpl2(4);
		obj.printBQ();
		
		Connection1Producer connObj = obj.getFromBQ();
		connObj.execute("my sample task");
		System.out.println(obj.getConnID());
		obj.printBQ();

		Connection1Producer connObj2 = obj.getFromBQ();
		connObj2.execute("my sample task 2");
		System.out.println(obj.getConnID());
		//connObj2.addToBQ(1);
		obj.printBQ();
		
		Connection1Producer connObj3 = obj.getFromBQ();
		connObj3.execute("my sample task 2");
		System.out.println(obj.getConnID());
		obj.printBQ();
		
	}
}
