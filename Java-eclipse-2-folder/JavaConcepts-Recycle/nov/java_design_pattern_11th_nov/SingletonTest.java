package java_design_pattern_11th_nov;
/**
 * 
 * @author Hariom Yadav - Nov 11, 2019
 *
 */
public class SingletonTest {
	public static void main(String[] args) {
		
		System.out.println("---singleton lazy initialiation test---");
		//Singleton obj = new SingletonTest();//error not able to create obj, since instance already present
		Singleton_lazy obj2 = Singleton_lazy.getObj();//so oly create instance coz obj will come from constructor
		//obj2 = obj2.getObj();
		System.out.println(obj2.hashCode());
		
		Singleton_lazy obj3 = Singleton_lazy.getObj();
		System.out.println(obj3.hashCode());
		
		System.out.println("---singleton eager initialization test---");
		Singleton_eager seObj = Singleton_eager.getObj();
		System.out.println(seObj.hashCode());
		
		Singleton_eager seObj2 = Singleton_eager.getObj();
		System.out.println(seObj2.hashCode());
		
		System.out.println("---singleton thread safe ini method---");
		Singleton_thread_safe_method sts = Singleton_thread_safe_method.getObj();
		System.out.println(sts.hashCode());
		
		Singleton_thread_safe_method sts2 = Singleton_thread_safe_method.getObj();
		System.out.println(sts2.hashCode());
		
		
		System.out.println("---singleton thread safe ini body---");
		Singleton_thread_safe_body sbody = Singleton_thread_safe_body.getObj();
		System.out.println(sbody.hashCode());
		
		Singleton_thread_safe_body sbody2 = Singleton_thread_safe_body.getObj();
		System.out.println(sbody2.hashCode());
		
		
	}
}
