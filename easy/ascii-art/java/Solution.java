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
        int L = in.nextInt();
        int H = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        String T = in.nextLine();
        
        int alphabetSize = 26;
        char[][][] alphabet = new char[H][L][alphabetSize];
        
        // Read alphabet
        for (int i = 0; i < H; i++) {
            String ROW = in.nextLine();
            for (int j = 0; j < alphabetSize; j++) {
                for (int k = 0; k < L; k++) {
                    alphabet[i][k][j] = ROW.charAt(j * L + k);
                }
            }
        }


        // Print word in ASCII-ART
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < T.length(); j++) {
                for (int k = 0; k < L; k++) {
                    int letterIndex = (int) T.toLowerCase().charAt(j) - 'a';
                    //System.err.println("Letter: " + T.toLowerCase().charAt(j) + ", " + "Letter index: " + letterIndex);
                    if (letterIndex >= 0 && letterIndex < alphabetSize) {
                        System.out.print(alphabet[i][k][letterIndex]);
                    }
                }
            }
            System.out.println("");
        }
    }
}