package july4th.singleton;

//1. private constructor
//2. public method for get instance
//3. private instance 
class SingletonEager{
	private static SingletonEager instance = new SingletonEager();//3
	private SingletonEager(){}//1
	public static SingletonEager getInstance() {//2
		return instance;
	}
}

public class SingletonExample {
	public static void main(String[] args) {
		SingletonEager instance1 = SingletonEager.getInstance();
		System.out.println(instance1);
		
		SingletonEager instance2 = SingletonEager.getInstance();
		System.out.println(instance2);
	}
}
