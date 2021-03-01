package june11th;

// https://docs.oracle.com/javase/tutorial/java/generics/types.html
interface Pair<K, V> {
	public K getKey();
	public V getValue();
}

class OrderedPair<K, V> implements Pair<K, V> {
	private K key;
	private V value;
	
	public OrderedPair() {
		super();
	}

	public OrderedPair(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return this.key;
	}

	public V getValue() {
		return this.value;
	}
	
}

public class Generic_Interface_MultipleTypeParameter {
	public static void main(String[] args) {
		Pair<Integer, String> pair1 = new OrderedPair(1, "Hariom");
		System.out.println(pair1.getKey());
		System.out.println(pair1.getValue());
		
		Pair<String, String> pair2 = new OrderedPair("Yadav", "Hariom");
		System.out.println(pair2.getKey());
		System.out.println(pair2.getValue());
	}
}
