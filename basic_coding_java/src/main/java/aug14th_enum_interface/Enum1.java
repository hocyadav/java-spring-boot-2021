package aug14th_enum_interface;

public enum Enum1 implements MyInterface{
	FIRST("google"),
	SECOND("apple");
	
	String str;
	
	private Enum1(String str) {
		this.str = str;
	}

	public String getMeth() {
		return this.str;
	}
}
