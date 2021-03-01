package aug14th_enum_interface;

public enum Enum3 {
	
	FIRST(1) {
		@Override
		public void fun1() {
			System.out.println("first sout");
		}
	},
	SEC(2) {
		@Override
		public void fun1() {
			System.out.println("second sout");
		}
	};
	
	int num;
	
	private Enum3(int i) {
	}
	
	public int getNum() {
		return this.num;
	}

	public abstract void fun1();

}
