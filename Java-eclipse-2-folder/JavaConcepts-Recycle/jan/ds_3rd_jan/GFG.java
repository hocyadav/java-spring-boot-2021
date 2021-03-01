package ds_3rd_jan;

import java.util.Scanner;

//not my code 
/**

Input:
1 			//int t=s.nextInt(); then--> while(t-- > 0)
4 5			//int n1=s.nextInt(); 
			//int n2=s.nextInt();
geek gesek	//String str1=s.next();
		    //String str2=s.next();

Output:
1			//System.out.println(util(str1,n1,str2,n2));
 */
public class GFG {
	public static void main (String[] args) {
		//code
		Scanner s=new Scanner(System.in);
		int t=s.nextInt();
		while(t-- > 0){
		    int n1=s.nextInt();
		    int n2=s.nextInt();
		    String str1=s.next();
		    String str2=s.next();
		    
		    System.out.println(util(str1,n1,str2,n2));
		}
	}
	
	public static int util(String str1, int n1, String str2, int n2){
	    int[][] dp=new int[n1+1][n2+1];
	    
	    for(int i=0;i<=n1;i++){
	        dp[i][0]=i;
	    }
	    for(int j=0;j<=n2;j++){
	        dp[0][j]=j;
	    }
	    for(int i=1;i<=n1;i++){
	        for(int j=1;j<=n2;j++){
	            if(str1.charAt(i-1)==str2.charAt(j-1)){
	                dp[i][j]=dp[i-1][j-1];
	            }
	            else{
	                dp[i][j]=Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]))+1;
	            }
	        }
	    }
	    return dp[n1][n2];
	}
}