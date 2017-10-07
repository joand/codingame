package fr.joand.root;

import java.util.*;
import java.io.*;
import java.math.*;


class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
            int maxMountainH = 0; 
            int maxMountainX = 0; 

        while (true) {
            for (int i = 0; i < 8; i++) {
                int mountainH = in.nextInt(); 
                if (maxMountainH <= mountainH) {
                    maxMountainH = mountainH;
                    maxMountainX = i;
                    
                }
            }

            System.out.println(maxMountainX);
            maxMountainH = 0; 
            maxMountainX = 0;
        }
    }
}
