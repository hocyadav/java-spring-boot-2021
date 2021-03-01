package july4th.singleton;

//1. private constructor
//2. public method for get instance
//3. private instance 
class SingletonLazy{
	private static SingletonLazy instance =null;//3
	private SingletonLazy(){}//1
	public static SingletonLazy getInstance() {//2 : this pattern is widely used but this will create 2 instance if mu;tiple thread can access same time
		if(instance == null) {
			instance = new SingletonLazy();
		}
		return instance;
	}
}

public class SingletonExample2 {
	public static void main(String[] args) {
		SingletonLazy instance1 = SingletonLazy.getInstance();
		System.out.println(instance1);
		
		SingletonLazy instance2 = SingletonLazy.getInstance();
		System.out.println(instance2);
	}
}
