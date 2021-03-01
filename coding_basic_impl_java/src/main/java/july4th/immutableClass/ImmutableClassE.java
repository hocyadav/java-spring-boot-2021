package july4th.immutableClass;

//1. final class
//2. private field + final fields
//3. only getter, no setter
//4. if field is other class than make deep copy while creating obj
public class ImmutableClassE {
	private final int val;
	private final String str;
	private final Engine engine;
	
	public ImmutableClassE(int val, String str, Engine engine) {
		this.val = val;
		this.str = str;
		this.engine = engine;//shallow copy
	}

	public int getVal() {
		return val;
	}

	public String getStr() {
		return str;
	}

	public Engine getEngine() {
		return engine;
	}
}
