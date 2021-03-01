package july1st;
//https://www.baeldung.com/java-pairs
public class CustomPair {
	String key;
	String val;
	
	public CustomPair() {}
	
	public CustomPair(String key, String val) {
		this.key = key;
		this.val = val;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}

	@Override
	public String toString() {
		return "CustomPair [key=" + key + ", val=" + val + "]";
	}
	
}
