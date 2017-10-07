import java.util.*;
import java.io.*;
import java.math.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        int H = in.nextInt();
        in.nextLine();
        String[] ROW = new String[H];
        String T = in.nextLine();
        int tLength = T.length();
        String[] slowo = new String[tLength];
        for(int i=0; i<tLength; i++)
        {
            slowo[i]=T.substring(i,i+1).toUpperCase();
        }        
        for (int i = 0; i < H; i++) {
            ROW[i] = in.nextLine();  
        }
        String[][] s = new String[1000][1000];
        int index=0;
        String test="";
        for(int i=0; i<tLength; i++)
        {
            index =((int) slowo[i].toCharArray()[0]-65)*L;
            if(index < 0) index = 104;
            for(int j=0; j<H; j++)
            {    
                if(index==101)  s[i][j] = ROW[j].substring(index,index+L);
                s[i][j] = ROW[j].substring(index,index+L);
                
            }   
        }
        
       for(int i=0; i<H; i++)
       {
             
            for(int j=0; j<tLength; j++)
            {
              test+= s[j][i]; 
            }
            test+="\n";
       }
       System.out.println(test);
       
    }
}
