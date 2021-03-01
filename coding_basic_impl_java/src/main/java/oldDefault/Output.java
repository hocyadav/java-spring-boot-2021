package oldDefault;
/**impatus interview 
 * convert to reference type 
 * **/
class test{
	int a;
	int b;
	void meth(int i , int j)//local
	{
		System.out.println(i +" - "+j);
		i *= 2;
		j /= 2;
		System.out.println(i +" -- "+j);
	}         
}   
class Output{
	public static void main(String args[]){
		test obj = new test();
		int a = 10;
		int b = 20;            
		obj.meth(a, b);
		System.out.println(a + " " + b);  //10, 20     
	}
}