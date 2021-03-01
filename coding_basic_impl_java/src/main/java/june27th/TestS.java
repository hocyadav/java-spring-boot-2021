package june27th;

import java.util.LinkedList;
import java.util.List;

public class TestS {
	public static void main(String[] args) {
		System.out.println("khakahs");
		
		String str = "ABCDEFGH";
		System.out.println("input : "+str);
		int t = 0;
		List<String> list = new LinkedList();

		for(int i =0; i < str.length();) {
			t = i + 2;
			String sub = str.substring(i, t);
			i = i + 2;
			list.add(sub);
		}
		System.out.println(list);
		
		int p = 0;
		int q = list.size()-1;
		while(p < q) {//n
			String temp = list.get(p);
			list.set(p, list.get(q));
			list.set(q, temp);
			p++;
			q--;
		}
		
		StringBuilder sb = new StringBuilder();
		System.out.println(list);
		for(String s : list) {
			sb.append(s);
		}
		System.out.println("output : "+sb.toString());
	}
}
