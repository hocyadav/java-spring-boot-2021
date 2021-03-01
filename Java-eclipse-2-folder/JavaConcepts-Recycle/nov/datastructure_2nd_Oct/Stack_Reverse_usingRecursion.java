package datastructure_2nd_Oct;
/**
 * 
 * @author Hariom Yadav - Nov 2, 2019
 *
 */
public class Stack_Reverse_usingRecursion {
	//know something
	static Stack obj = new Stack();
	//does something
	public static void main(String[] arg) {
		obj.push("4");obj.traverse();
		obj.push("3");obj.traverse();
		obj.push("2");obj.traverse();
		obj.push("1");obj.traverse();
		reverse(obj);
		obj.traverse();
	}

	//imp : store variable in fun call stack
	private static void reverse(Stack obj) {
		if(obj.isEmpty()) {
			return;
		}else {//1.get top element, 2.delete top element, 3.call 1-2 again(i.e. call recursion), 4.insert_at_bottom
			String s = obj.top();//1
			obj.pop();//2
			
			reverse(obj);//3 : recursion - fcs : and before that we are storing its value in string s
			
			insert_at_bottom(s);
		}
	}

	//store variable in fun call stack
	private static void insert_at_bottom(String s) {
		if(obj.isEmpty()) {//if empty : simply insert at bottom of stack : push
			obj.push(s);
		}else {
			String topStr = obj.top();
			obj.pop();
			
			insert_at_bottom(s);//FCS : before that we are storing its value
			
			obj.push(topStr);
		}
	}
}
