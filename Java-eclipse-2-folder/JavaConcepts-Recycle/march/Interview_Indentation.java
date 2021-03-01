
/*
* The string at line 5 contains a valid java program
* You have to write a method to indent the program(string)
* You have to take care of following scenarios
*   1. When you encounter a '{', a new line should be inserted and 4 spaces should be put in the next line
*   2. For subsequent '{', we should increase the tab space by 4 spaces in the next line. We need to ensure that each code block is in right indentation level
*   3. For a ';' a new line should be inserted and next line should begin on right indentation level
*   4. For a '}' a new line should be inserted and 4 spaces should be removed from the next line

If the program is implemented correctly, the output of Indent method will look like following

public class PrettyPrint {
    public static void main(String args[]) { 
        System.out.println("Hello World"); 
        String input = "This is a sample code"; 
        System.out.println(Indent(input));
    } 
    
    public static String Indent(String input) { 
        return "Indented: " + input;
    } 
}

*
* NOTES:
* 1. You can assume that the string is a valid java program and there are no compilation error possible
* 2. You can do Google search for syntax/library calls
*/


public class Interview_Indentation {

	public static void main(String[] args) {
		System.out.println("Hello World");
        String input = "public class PrettyPrint { public static void main(String args[]) { System.out.println(\"Hello World\"); String input = \"This is a sample code\"; System.out.println(Indent(input)); } public static String Indent(String input) { return \"Indented: \" + input; } }";
        System.out.println(Indent(input));
	}
	
    public static String Indent(String input) {
        String indentedOutput = "";
        //Code goes here
        
        StringBuilder str = new StringBuilder("");
        int count = 0;
        String s = "    ";
        String s2 = "";
        for(int i = 0; i < input.length(); i++) {
        	if(input.charAt(i) == '{') {
        		count++;
        		String s3 = "";
        		for(int j = 0; j < count; j++) {
        			s3 += s;
        		}
        		s2 = s3;
        		str.append(input.charAt(i) +"\n" + s2);
        		//System.out.println(str.toString());
        	}else if(input.charAt(i) == ';' && count > 0) {
        		str.append(input.charAt(i)+"\n");
        		str.append(s2);
        	}else if(input.charAt(i) == '}') {
        		count--;
        		String s3 = "";
        		for(int j =0; j < count-1; j++) {
        			s3 += s;
        		}
        		s2 = s3;
        		str.append(input.charAt(i)+"\n");
        	}else {
        		str.append(input.charAt(i));
        	}
        }
        
        System.out.println(str.toString());
        
        return indentedOutput;
    }
    
    
    
	

}
