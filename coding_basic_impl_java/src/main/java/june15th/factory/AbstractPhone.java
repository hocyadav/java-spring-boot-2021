package june15th.factory;

public abstract class AbstractPhone {
	protected String phoneName;//same package
	
	public AbstractPhone() {//imp
		createObj();
	}

	abstract void createObj();//imp

	@Override
	public String toString() {
		return "AbstractPhone [phoneName=" + phoneName + "]";
	}

}
