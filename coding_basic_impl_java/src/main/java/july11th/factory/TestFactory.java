package july11th.factory;

public class TestFactory {
	public static void main(String[] args) {
		ParentClass obj = new FactoryClass().getInstance("child1");
		obj.show();
		
		ParentClass obj2 = new FactoryClass().getInstance("child2");
		obj2.show();
		
		ParentClass obj3 = new FactoryClass().getInstance("child3");//child3 type not present
		if(obj3!=null) obj3.show();
		else System.out.println("obj not created");
	}
}
/*
child1 object show..
child 2 class show..
obj not created

*/