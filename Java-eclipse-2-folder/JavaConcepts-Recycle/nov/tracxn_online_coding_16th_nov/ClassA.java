package tracxn_online_coding_16th_nov;

import java.util.Scanner;

public class ClassA {
public static void main(String[] args) {
	

    Scanner in = new Scanner(System.in);
    int numberOfDays = in.nextInt();
    
    int inCount  = 0;
    
    int fat = 0;
    int fi = 0;
    int ca = 0;
    
    for (int i = 0; i < numberOfDays; i++) {
        boolean flag = false;
        
        String ingredient = in.next();
        
        if(ingredient.startsWith("FAT")){
            ++fat;
            ++inCount;
            
            if(inCount >= 3){
                
                if(fat >= 2){
                    fat = fat - 2;
                    flag = true;
                    inCount = inCount - 3;
                    if(fi >= 1)
                        --fi;
                    else if(ca >= 1)
                        --ca;
                else
                    --fat;
                }else if(fi >=2 ){
                    
                    fi = fi - 2;
                    flag = true;
                    inCount = inCount - 3;
                    if(fat >=1)
                        --fat;
                    else if(ca >= 1)
                        --ca;
                    else
                    	--fi;
                    
                }else if(ca >= 2){
                
                    ca = ca - 2;
                    flag = true;
                    inCount = inCount - 3;
                    if(fi >=1)
                        --fi;
                else if(fat >= 1)
                    --fat;
                else
                    --ca;
                }
            }
            
        }else if(ingredient.startsWith("FIB")){
            ++fi;
            ++inCount;
            
            if(inCount >= 3){
                
                if(fat >= 2){
                    fat = fat - 2;
                    flag = true;
                    inCount = inCount - 3;
                    if(fi >=1)
                        --fi;
                    else if(ca >= 1)
                        --ca;
                else
                    --fat;
                }else if(fi >=2 ){
                    
                    fi = fi - 2;
                    flag = true;
                    inCount = inCount - 3;
                    if(fat >=1)
                        --fat;
                else if(ca >= 1)
                        --ca;
                else
                    --fi;
                    
                }else if(ca >= 2){
                
                    ca = ca - 2;
                    flag = true;
                    inCount = inCount - 3;
                    if(fi >= 1)
                        --fi;
                else if(fat >= 1)
                    --fat;
                else
                    --ca;
                }
            }
            
        }else{
            ++ca;
            ++inCount;
            
            if(inCount >= 3){
                
                if(fat >= 2){
                    fat = fat - 2;
                    flag = true;
                    inCount = inCount - 3;
                    if(fi >= 1)
                        --fi;
                    else if(ca >= 1)
                        --ca;
                else
                    --fat;
                }else if(fi >=2 ){
                    
                    fi = fi - 2;
                    flag = true;
                    inCount = inCount - 3;
                    if(fat >= 1)
                        --fat;
                else if(ca >= 1)
                        --ca;
                else
                    --fi;
                    
                }else if(ca >= 2){
                
                    ca = ca - 2;
                    flag = true;
                    inCount = inCount - 3;
                    if(fi >= 1)
                        --fi;
                    else if(fat >= 1)
                    	--fat;
                    else
                    	--ca;
                }
            }
            
        }
        
    if(flag ==  true )
        System.out.print(1);
    else
        System.out.print(0);
    }
		
	
}
}
