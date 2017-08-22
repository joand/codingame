package fr.joand.root;

import java.util.Scanner;

/**
 * Poker MDF 2015
 */
public class App {
    public static void main(String[] argv) throws Exception {

        Scanner sc = new Scanner(System.in);

        int sommeDepart = Integer.parseInt(sc.nextLine());
        int nbTourMax = Integer.parseInt(sc.nextLine());

        for (int tourNb = 0; tourNb < nbTourMax; tourNb++) {
            String line = sc.nextLine();
            String[] splitedLine = line.split(" ");
            int laMise = Integer.parseInt(splitedLine[0]);
            int gain = Integer.parseInt(splitedLine[1]);

            sommeDepart = sommeDepart - laMise + gain;
        }
    /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
        System.out.println(sommeDepart);
    }
}
