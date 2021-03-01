package jan_11th;

//  Definition for a binary tree node.

class TreeNode{
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { 
    	  val = x; 
      }
 }
 
class Solution {
    int sum = 0;
    
    public int sumEvenGrandparent(TreeNode root) {
        inorder(root);
        return sum;
    }
    
    public void inorder(TreeNode root){
        if(root == null) 
            return;
        
        //check for even
        if(root.val % 2 == 0){
            
            if(root.left != null && root.left.left != null)
                sum += root.left.left.val;
            
            if(root.left != null && root.left.right != null)
                sum += root.left.right.val;
            
            if(root.right != null && root.right.left != null)
                sum += root.right.left.val;
            
            if(root.right != null && root.right.right != null)
                sum += root.right.right.val;    
        
        }else{//not even
            inorder(root.left);
            inorder(root.right);
        }
        
    }
    
}

public class Leet {

	public static void main(String[] args) {
		TreeNode obj = new TreeNode(6);
		
	}

}
