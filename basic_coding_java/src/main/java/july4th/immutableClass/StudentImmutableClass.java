package july4th.immutableClass;
//1. make class final : else any one can extend and probality that value can be change by child class
//2. make field private + final : else own class obj can change its value , obj.id = 12
//3  only getter, no setter : else by using setter anyone can change value
public final class StudentImmutableClass {
	private final int id;
	private final String name;
	
	public StudentImmutableClass(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}
}
