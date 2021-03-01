package july25th;

public class Test {
	public static void main(String[] args) {
		ConnectionImpl obj = new ConnectionImpl(4);
		obj.printBQ();
		obj.getFromBQ(); obj.printBQ();
		obj.getFromBQ(); obj.printBQ();
		obj.getFromBQ(); obj.printBQ();
		obj.getFromBQ(); obj.printBQ();
		obj.addToBQ(1);
		obj.getFromBQ(); obj.printBQ();
	}
}
