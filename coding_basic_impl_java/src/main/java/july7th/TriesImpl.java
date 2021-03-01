package july7th;

class TNode {
	char val;
	boolean isWord;
	TNode[] children = new TNode[26];
	
	public TNode() {}
	public TNode(char c) {
		this.val = c;
	}
}

class TrieTree {
	TNode root = new TNode();
	
	public TrieTree() {
		root.val = ' ';
	}
	
	//insert : traverse from root -> fill new node -> update pointer -> and last update isword = true
	public void insert(String str) {
		TNode it = root;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			
			if(it.children[c - 'a'] == null) {
				TNode nn = new TNode(c);
				it.children[c - 'a'] = nn;
			}
			it = it.children[c - 'a'];//move pointer to child node
		}
		it.isWord = true;
	}
	
	public boolean search(String str) {
		TNode it = root;
		
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if(it.children[c - 'a'] == null) return false;
			it = it.children[c - 'a'];//update pointer
		}
		return it.isWord;
	}
	
	public boolean prefix(String str) {
		TNode it = root;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if(it.children[c - 'a'] == null) return false;
			it = it.children[c - 'a'];//update pointer
		}
		return true;
	}
	
}


public class TriesImpl {
	public static void main(String[] args) {
		TrieTree tree = new TrieTree();
		
		tree.insert("hari");
		tree.insert("hariom");
		tree.insert("harry");
		tree.insert("hariomyadav");
		//tree.insert("hariom yadav");
		System.out.println(tree.search("hari"));
		System.out.println(tree.prefix("har"));
		System.out.println(tree.search("hariom"));
		System.out.println(tree.search("hariomyadav"));
		//System.out.println(tree.search("hariom yadav"));
		
	}
}
