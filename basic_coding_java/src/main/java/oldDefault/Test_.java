package oldDefault;

public class Test_ {
	public static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		String str = "developer";
		
		String s2 = rec(str, 0, str.length());
		System.out.println(s2);
		
		
	}
	
	
	private static String rec(String s, int i, int j) {
		if(i > j) return "";
		
		String sub = s.substring(i,i+1);
		String tt = rec(s, i+1, j);
		sb.append(tt + sub);
		
		return tt;
	}
}

