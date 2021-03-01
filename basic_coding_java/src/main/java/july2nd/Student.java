package july2nd;

public class Student {
	int roll;
	String name;
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(int roll, String name) {
		super();
		this.roll = roll;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Student [roll=" + roll + ", name=" + name + "]";
	}
	public int getRoll() {
		return roll;
	}
	public void setRoll(int roll) {
		this.roll = roll;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
