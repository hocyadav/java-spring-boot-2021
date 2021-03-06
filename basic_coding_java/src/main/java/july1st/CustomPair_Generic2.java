package july1st;

public class CustomPair_Generic2<K, V> {
	K key;
	V val;
	
	//public CustomPair_Generic2() {}

	public CustomPair_Generic2(K key, V val) {
		super();
		this.key = key;
		this.val = val;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getVal() {
		return val;
	}

	public void setVal(V val) {
		this.val = val;
	}

	@Override
	public String toString() {
		return "CustomPair_Generic [key=" + key + ", val=" + val + "]";
	}
	
	
	
}
