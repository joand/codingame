package java;

import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution2 {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of temperatures to analyse
        int diff = 5527;
        int temperature = 0;
        for (int i = 0; i < n; i++) {
            int t = in.nextInt();   // a temperature expressed as an integer ranging from -273 to 5526
            
            if (t < -273 || t > 5526)   {
                continue;
            }
            
            int tempdiff = Math.abs(0 - t); 
            if (tempdiff < diff)    {
                diff = tempdiff;
                temperature = t;
            } else if (tempdiff == diff)    {
                if (i > temperature)    {
                    temperature = t;
                }
            }
            
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(temperature);
    }
}