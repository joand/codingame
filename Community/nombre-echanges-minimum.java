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
        int n = in.nextInt();
        
        int[] tableau = new int[n];
        
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            tableau[i] = x;
        }
        System.err.println("TABLEAU INI: " + afficher(tableau));
        System.err.println(estTrie(tableau));

        int nbEchange = 0 ;
        
        for (int i = 0; i < tableau.length; i++) {
            System.err.println(" valeur i : " + i + " " + estTrie(tableau));
            if(tableau[i] == 0 && estTrie(tableau)==false){
                boolean trouve = false;
                for (int j = tableau.length-1; j>=0 ; j--) {
                    if(tableau[j] == 1 && trouve==false){
                        trouve = true;
                        tableau[i] =1;
                        tableau[j] =0;
                        nbEchange++;
                        System.err.println("ECHANGE N°: " + nbEchange + " soit : " + afficher(tableau));
                    }
                }
            }
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(nbEchange);
    }
    
    public static boolean estTrie(int[] tableau){
        boolean answer = true;

        for (int i = 1; i < tableau.length; i++) {
            if(tableau[i-1] == 0 && tableau[i] == 1){
                answer = false;
            }
        }
        return answer;

    }
    
    public static String afficher(int[] tableau){
        String answer = "";
        for (int i = 1; i < tableau.length; i++) {
            answer += Integer.toString(tableau[i]);
            answer += " ";
        }
        return answer;
    }
}