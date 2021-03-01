package june18th;

import junit.framework.TestCase;

public class UnitTest2 extends TestCase{
	protected int value1;
	protected int value2;
	
	//assign the value
	protected void setValue() {//run before every test invocation
		value1 = 1;
		value2 = 1;
	}
	
	public void test2() {
		double val = value1 + value2;
		assertTrue(val == 2);
	}
}
