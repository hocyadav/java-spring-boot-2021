package com.hariom;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**Paypal : last round : data structure for BT**/
class Node{
	int data;
	Node left;
	Node right;
	
	public Node(int data) {
		super();
		this.data = data;
	}
}

/** impl **/
class BTree{
	Node root;
	
	public void levelOrder__() {
		Queue<Node> qq = new LinkedList();
		qq.add(root);
		System.out.print("level order :");
		while(!qq.isEmpty()) {
			Node nn = qq.poll();//offer add, remove, peek, poll=peek+remove
			System.out.print(nn.data+" ");
			
			if(nn.left != null) qq.add(nn.left);
			if(nn.right != null) qq.add(nn.right);
		}
		System.out.println();
	}
	
	public void levelOrder__PrintLevelWise() {
		Queue<Node> qq = new LinkedList<>();
		qq.add(root);
		
		while(!qq.isEmpty()) {
			int levelSize = qq.size();//1
			List<Integer> list = new ArrayList<>();
			for(int i = 0; i < levelSize; i++) {
				Node nn = qq.poll();
				list.add(nn.data);
				
				if(nn.left != null) qq.add(nn.left);
				if(nn.right != null) qq.add(nn.right);
			}
			System.out.println(list);
			list.clear();
		}
	}
}

public class TestClass {
	public static void main(String[] args) {
		BTree tree = new BTree();
		tree.root = new Node(10);
		tree.root.left = new Node(6);
		tree.root.left.left = new Node(68);
		tree.root.left.right = new Node(68);
		tree.root.right = new Node(7);
		tree.root.left.left.left = new Node(69);
		tree.levelOrder__();
		tree.levelOrder__PrintLevelWise();
	}
}
