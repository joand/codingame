import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int min = Integer.MAX_VALUE;
        List<Integer> elements = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
            int pi = in.nextInt();
            elements.add(pi);
        }
        
        Collections.sort(elements);
        
        for (int i = 0; i < elements.size() - 1; i++) {
            int diff = Math.abs(elements.get(i) - elements.get(i + 1));
            if (diff < min) {
                min = diff;
            }
        }

        System.out.println(min);
    }
}