package fr.joand.root;

import java.util.Scanner;

public class Answer {
    public static void main( String[] argv ) throws Exception {
        String  line;
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            line = sc.nextLine();
            int N = Integer.parseInt(line);
            String[] tags = new String[N];
            for (int i =0; i < N; i++) {
                tags[i] = sc.nextLine();
            }

            for (int i = 0; i < N-40; i++) {
                int occ = 0;
                String t = tags[i];
                int maxJ = Math.min(N,  i+60);
                for (int j = i; j < maxJ; j++) {
                    if (tags[j].equals(t)) occ++;
                }
                if (occ >= 40) {
                    System.out.println(t);
                    return;
                }
            }
            System.out.println("Pas de trending topic");
            break;
        }
	/* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }
}
