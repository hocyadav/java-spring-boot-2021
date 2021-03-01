package java_12th_nov_Overriding;
/**
 * 
 * @author Hariom Yadav - Nov 12, 2019
 *
 */

//IMP : private --> default - protected - public 
//static can be only variable and method , that can be on class level , because if we write inside method name then we can access outside 
public class Test1 {
	public void funPublic() {
		//static int iii = 0;//static variable can't be inside , static can only be variable and method and that will be on class level
		System.out.println("1");
		
	}
	private void funPri() {
		System.out.println("11");
	}
	
	void funDef() {
		System.out.println("11");
	}
	
	protected void funProtected() {
		System.out.println("11");
	}
}

class Test2 extends Test1{
	
	public void funPublic() {//public 
		System.out.println("2");
		super.funPublic();
	}
	private  void funPri() {//private , defa(no keyword required), prot, public 
		System.out.println("22");
	}
	
	
	protected void funDef() {//def, pro, pul
		System.out.println("11");
	}
	
	protected void funProtected() {//protected , public 
		System.out.println("11");
	}
	
}