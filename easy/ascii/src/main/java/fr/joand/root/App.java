package fr.joand.root;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App
{
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int Largeur = in.nextInt();
        in.nextLine();
        int Hauteur = in.nextInt();
        in.nextLine();
        String Texte = in.nextLine().toUpperCase(); // composed of N ASCII characters.

        String ref = "ABCDEFGHIJKLMNOPQRSTUVWXYZ?";

        for (int rowNumber = 0; rowNumber < Hauteur; rowNumber++) {
            String ROW = in.nextLine();
            Map<Character, String> rowDico = new HashMap<>();
            int index = 0;
            for (int charNumber = 0; charNumber < ROW.length(); charNumber = charNumber + Largeur) {
                String letter = ROW.substring(charNumber, charNumber + Largeur);
                rowDico.put(ref.charAt(index), letter);
                index++;
            }
            //System.out.println(rowDico.keySet());
            for (int charNumber = 0; charNumber < Texte.length(); charNumber++) {
                String toDisplay = rowDico.get(Texte.charAt(charNumber)) == null ?
                        rowDico.get('?') : rowDico.get(Texte.charAt(charNumber));
                System.out.print(toDisplay);
            }
            System.out.println();
        }
    }
}
