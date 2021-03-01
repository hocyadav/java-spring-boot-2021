package jan_12th;

public class Leet {
	public static void main(String[] args) {
		int r = minFlips(43, 78, 11);
		System.out.println("r :"+r);
	}
	
	public static int minFlips(int a, int b, int c) {
        
        String str_a = String.format("%30s", Integer.toBinaryString(a)).replace(' ', '0');//0010
        String str_b = String.format("%30s", Integer.toBinaryString(b)).replace(' ', '0');//0110
        String str_c = String.format("%30s", Integer.toBinaryString(c)).replace(' ', '0');//0101
        
        System.out.println(str_a);
        System.out.println(str_b);
        System.out.println(str_c);
        
        int count = 0;
        
        for(int i=0; i<str_c.length(); i++){
            if(str_c.charAt(i) == '0'){//output value is 0
                if(str_a.charAt(i) == '0' && str_b.charAt(i) == '0'){
                    continue;
                }else if(str_a.charAt(i) == '1' && str_b.charAt(i) == '1')
                		count +=2;
                	else
                		count++;
            }else{//output value is 1
                if(str_a.charAt(i) == '1' || str_b.charAt(i) == '1')
                    continue;
                else
                	count++;
            }
        }
        return count;
    }
	
}
