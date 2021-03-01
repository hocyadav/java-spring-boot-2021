import java.util.*;

public class Test {

	public static void main(String[] args) {
		
//		int[][] arr= {{1,5},{1,5},{1,5},{2,3},{2,3}};
//		
//		System.out.println(maxEvents(arr));
		
		String s[] = {"abc", "cnc", "abc"};
		String s2[] = solve(s, 2);
				
	
	}

	
	public static int maxEvents(int[][] events) {
       
        Arrays.sort(events,(a,b)->{
        	if(a[1]!=b[1]) {
        		return b[1]-a[1];       		
        	}
        	return b[0]-a[0];
        });
              
        
        
        int result = 1;
        int globalEnd=events[0][1];
        
        
        for(int i = 1; i < events.length ; i++) {
        	
        	if(events[i][1]<globalEnd ) {
        		result++;
        		globalEnd=events[i][1];       		
        	}else if(events[i][0]<globalEnd){
        		result++;
        		globalEnd--;        		
        	}
        	System.out.println(result+" "+globalEnd);
        	
         
        }
        
        return result;
        
    }

	public static String[] solve(String[] S, int N) {
        Map<String, Integer> map = new HashMap();
        
        for(String s : S) {
            int count = map.getOrDefault(s, 0);
            map.put(s, count + 1);
        }
        
        Set<Map.Entry<String, Integer>> set = map.entrySet();
        List<Map.Entry<String, Integer>> list = new ArrayList(set);
        
        
        
        Collections.sort(list,(x,y)->{
            if(y.getValue()-x.getValue()!=0){
                return y.getValue()-x.getValue();
            }
            return x.getKey().compareTo(y.getKey());
        });
        System.out.println(list);
        String[] res = new String[set.size()];
        
        int i=0;
        for(Map.Entry<String, Integer> st : list) {
            res[i++]=st.getKey();
            
        }
        return res;
    }

}
