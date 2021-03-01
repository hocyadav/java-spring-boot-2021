package july4th.singleton;

//1. private constructor
//2. public method for get instance
//3. private instance 
class SingletonLazySync{
	private static SingletonLazySync instance =null;//3
	private SingletonLazySync(){}//1
	public static synchronized SingletonLazySync getInstance() {//2 : method level sync : both read and write are blocked
		if(instance == null) {
			instance = new SingletonLazySync();
		}
		return instance;
	}
}

public class SingletonExample3 {
	public static void main(String[] args) {
		SingletonLazySync instance1 = SingletonLazySync.getInstance();
		System.out.println(instance1);
		
		SingletonLazySync instance2 = SingletonLazySync.getInstance();
		System.out.println(instance2);
	}
}
