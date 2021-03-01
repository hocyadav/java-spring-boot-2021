package july5th;

class TrieNode{
	char val;
	boolean isWord;
	TrieNode[] children = new TrieNode[26];
	public TrieNode() {}
	public TrieNode(char c ) {
		this.val = c;
	}
}

class TrieIMPL{
	TrieNode root = new TrieNode();
	
	public TrieIMPL() {
		root.val = ' ';
	}
	
	//iterate 
	public void insert(String str) {
		TrieNode it = root;
		for(int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if(it.children[c - 'a'] == null) {
				it.children[c - 'a'] = new TrieNode(c);
			}
			it = it.children[c - 'a'];//move pointer
		}
		
		it.isWord = true;
	}
	
	public boolean search(String str) {
		TrieNode it = root;
		for(int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if(it.children[c - 'a'] == null) {
				return false;
			}
			it = it.children[c - 'a'];
		}
		return it.isWord;
	}
	
	public boolean prefix(String str) {
		TrieNode it = root;
		for(int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if(it.children[c - 'a'] == null) return false;
			it = it.children[c - 'a'];
		}
		return true;
	}
}

public class Trie {
	public static void main(String[] args) {
		TrieIMPL obj = new TrieIMPL();
		obj.insert("hari");
		System.out.println(obj.search("hari"));
		System.out.println(obj.search("har"));
		System.out.println(obj.prefix("har"));
	}
}
