import java.util.*;
import java.io.*;
import java.math.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
         List<Integer> power = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
            int Pi = in.nextInt();
            power.add(Pi);
        }
        
        Collections.sort(power);
        int answer = Integer.MAX_VALUE;
        
        for (int i = 0; i < power.size()-1; i++) {
            int current = power.get(i);
            int next = power.get(i+1);
            int D = next - current;
            if ( D < answer) {
                answer = D;
            } 
        } 
        
System.out.println(answer);
    }
}
