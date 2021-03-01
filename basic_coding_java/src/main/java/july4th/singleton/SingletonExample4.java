package july4th.singleton;

//1. private constructor
//2. public method for get instance
//3. private instance 
class SingletonLazySync2{
	private static SingletonLazySync2 instance =null;//3
	private SingletonLazySync2(){}//1
	public static SingletonLazySync2 getInstance() {//2 : block level sync : only write is blocked, read is open for all
		if(instance == null) {
			synchronized (SingletonExample2.class) {
				if(instance == null) {
					instance = new SingletonLazySync2();
				}
			}
		}
		return instance;
	}
}

public class SingletonExample4 {
	public static void main(String[] args) {
		SingletonLazySync2 instance1 = SingletonLazySync2.getInstance();
		System.out.println(instance1);
		
		SingletonLazySync2 instance2 = SingletonLazySync2.getInstance();
		System.out.println(instance2);
	}
}
