package july24th;
//name segment means each node store sum in segment form, 
//i.e. sum of segment size 1 for leaf, then move up 1 level from leaf segment of size 2 and so on
// https://youtu.be/Ic7OO3Uw6J0

//data structure / object for segment node 
class Node {
	int start, end;
	int sum;
	Node left, right;
	
	public Node(int s, int e) {
		start = s;
		end = e;
	}
}

//impl of above DS
class TreeImpl {
	Node root;
	
	public void buildSegmentTree(int[] arr, int start, int end) {
		root = rec(arr, start, end);
	}

	private Node rec(int[] arr, int start, int end) {
		if(start > end) return null;
		
		Node root_ = new Node(start, end);
		if(start == end) {//leaf
			root_.sum = arr[start];
		} else {
			int mid = start + (end - start)/2; //start + jump to mid..mid is (end-start)/2
			root_.left = rec(arr, start, mid);
			root_.right = rec(arr, mid+1, end);
			root_.sum = root_.left	.sum + root_.right.sum;//1
		}
		return root_;
	}
	
	public void updateSegmentTree(int index, int newVal ) {
		rec_upd(root, index, newVal);
	}

	private void rec_upd(Node root, int index, int newVal) {
		if(root.start == root.end) {
			root.sum = newVal;
			return;
		}
		
		int mid = root.start + (root.end - root.start)/2;//here mid we are not sending in recursion only for calling left or right
		
		if(index <= mid) 
			rec_upd(root.left, index, newVal);
		else 
			rec_upd(root.right, index, newVal);
		root.sum = root.left.sum + root.right.sum;
	}
	
	public int sumRange(int start, int end) {
		return sum_rec(root, start, end);
	}

	private int sum_rec(Node root, int start, int end) {
		if(root == null) //optional - already we handle incaller method
			return 0;
		if(root.start == start && root.end == end) 
			return root.sum;
		//case 1
		int mid = root.start + (root.end - root.start)/2;
		if(end <= mid) 
			return sum_rec(root.left, start, end);
		else if(start >= mid+1)
			return sum_rec(root.right, start, end);
		else
			return sum_rec(root.right, mid+1, end) + sum_rec(root.left, start, mid);
	}
	
	
	public void printSegmentTree_inorder() {
		rec_inorder(root);
	}

	private void rec_inorder(Node root) {
		if(root == null) 
			return;
		rec_inorder(root.left);
		System.out.println(root.start+","+root.end+" : "+root.sum);
		rec_inorder(root.right);
	}
}

public class SegmentTree_RangeSumQuery {
	public static void main(String[] args) {
		TreeImpl tree = new TreeImpl();
		int[] arr = {1,0,2,5,3};
		tree.buildSegmentTree(arr, 0, arr.length - 1);
		tree.printSegmentTree_inorder();
		int i = tree.sumRange(1, 4);
		System.out.println(i);
	}
}
/**
0,0 : 1
0,1 : 1
1,1 : 0
0,2 : 3
2,2 : 2
0,4 : 11
3,3 : 5
3,4 : 8
4,4 : 3

 */
