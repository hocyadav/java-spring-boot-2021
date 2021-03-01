package ds_5th_jan;

public class Alphabet_step {

	public static void main(String[] args) {
		//char to number : m1 , input : a,b,c,... -> output : 0,1,2,3..
		// input - 'a'
		System.out.println('a'-'a');
		System.out.println('b'-'a');
		System.out.println('c'-'a');//c -a = 2
		System.out.println('d'-'a');//d -a
		
		//m2
		System.out.println('c'-97);//c -97 = 2
		
		//number to char : input 0,1,2,3 ... --> output : a,b,c
		// (char)(input + 97)
		// (char)(input + (int)'a')
		System.out.println((char)(97+0));
		System.out.println((char)(97+1));
		System.out.println((char)(97+2));
		
		//know ascii value : just type case to int
		System.out.println((int)'a');
		System.out.println((int)'z');
		
		System.out.println((int)'A');
		System.out.println((int)'Z');
		StringBuilder sb = new StringBuilder("");
		System.out.println(sb.append('a').toString());
		
		String str = "10#11#12#123456789";
		String result = freqAlphabets(str);
		System.out.println(result);
		
		String str1 = "12";
		int i = Integer.parseInt(str1);
		System.out.println(i);
		
	}
	
	public static String freqAlphabets(String s) {
        char[] carr = s.toCharArray();
        System.out.println(carr);
        StringBuilder sb = new StringBuilder("");
        
        for(int i=0; i<carr.length; i++){
        	System.out.println(i);
            if(carr[i+2] != '#'){//normal : a-i
            	       	
                int v = Integer.parseInt(String.valueOf(carr[i])) - 1;
                char c = (char)(v + 97);
                sb.append(c);
                System.out.println("v : "+v+" c : "+c+" sb : "+sb.toString());
                
                
                int v2 = Integer.parseInt(String.valueOf(carr[i+1])) - 1;
                char c2 = (char)(v2 + 97);
                sb.append(c2);
                System.out.println("v2 : "+v2+" c2 : "+c2+" sb : "+sb.toString());
                
                
                int v3 = Integer.parseInt(String.valueOf(carr[i+2])) - 1;
                char c3 = (char)(v3 + 97);
                sb.append(c3);
                System.out.println("v2 : "+v3+" c2 : "+c3+" sb : "+sb.toString());
                i +=2;
                
            }else{//j-z block
                int v = Integer.parseInt(String.valueOf(carr[i])+String.valueOf(carr[i+1])) - 1;
                char c = (char)(v + 97);
                sb.append(c);
                System.out.println("v : "+v+" c : "+c+" sb : "+sb.toString());
                i += 2;
            }
        }
        return sb.toString();
    }

}
