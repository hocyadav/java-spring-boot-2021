package aug14th_enum_interface;

public enum Enum2 implements MyInterface{
	PHONE1("pixel"),
	PHONE2("ipone11");

	String str;
	Enum2(String string) {
		this.str = string;
	}
	public String getMeth() {
		return this.str;
	}
}
