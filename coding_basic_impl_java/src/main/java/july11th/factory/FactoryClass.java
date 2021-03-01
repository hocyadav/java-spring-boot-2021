package july11th.factory;

public class FactoryClass {
	public ParentClass getInstance(String type) {
		ParentClass instance = null;
		if(type.equals("child1")) {
			return new Child1();
		} else if(type.equals("child2")) {
			return new Child2();
		}
		return instance;
	}
}
