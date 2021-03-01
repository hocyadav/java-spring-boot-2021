package ds_4th_Oct;
/**
 * 
 * @author Hariom Yadav - Nov 4, 2019
 *
 */
//ds
class NodeBT{
	int data;
	NodeBT left, right;
	//does something
	NodeBT(int v){
		data = v;
	}
}

//use of ds
class BinaryTree{
	//know something
	NodeBT root;
	
	//does something
	void print(){//link list print method - here it is printing left most or right most
		NodeBT temp = root;
		System.out.print("");
		while(temp!=null) {
			System.out.print(temp.data+" ");
			temp = temp.right;
		}
		System.out.println("");
	}
	
	void inorder() {
		inorder_rec(root);
	}
	NodeBT t1 = null;
	NodeBT t2  = null;

	private void inorder_rec(NodeBT root2) {
		if(root2 == null) return;
		else {
			t1 = root2.left;
			t2 = root2.right;//so this is not possible - so we have 2 type one is see revere stack proble and another here we are senfing same address
			inorder_rec(t1);//FCS - top left : thing rec calling as FCS only
			System.out.print(t1.data+" ");//FCS - top left data
			inorder_rec(t2);//
		}
		System.out.print("");
	}

	public void postoder() {
		postorder_rec(root);
	}

	private void postorder_rec(NodeBT root3) {
		if(root3 == null) {
			return;
		}else {
			postorder_rec(root3.left);//fun call stack - top element 
			postorder_rec(root3.right);//FCS top element right			
			System.out.print(root3.data+" ");
		}
		System.out.print("");
	}

	public void preorder() {
		preorder_rec(root);
	}

	private void preorder_rec(NodeBT root4) {
		if(root4 == null) {
			return;
		}else {
			preorder_rec(root4.right);
			System.out.print(root4.data+" ");
			preorder_rec(root4.left);
		}
	}
	
}


public class BinaryTree_Impl {
	public static void main(String[] args) {
		BinaryTree obj = new BinaryTree();
		NodeBT nn = new NodeBT(1);
		NodeBT ll = new NodeBT(2);
		NodeBT rr = new NodeBT(3);
		
		
		obj.root = nn;
		//obj.print();
		obj.root.left = ll;
		obj.root.right = rr;
		
		//NodeBT lll = new NodeBT(4);
		//obj.root.left.left = lll;
		
		//obj.print();
		obj.inorder();
		System.out.println("");
		obj.postoder();
		System.out.println("");
		//obj.preorder();
		
	}

}
