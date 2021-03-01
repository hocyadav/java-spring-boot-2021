package feb_14th_night;
//1. class final
//2. field as final

public final class Employee {
	
	final String name;
	final int phoneNumber;
	//final Date d;
	
	public Employee(String name, int phoneNumber) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPhoneNumber() {
		return phoneNumber;
	}
	
}
