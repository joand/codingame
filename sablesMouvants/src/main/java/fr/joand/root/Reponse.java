package fr.joand.root;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reponse {
    private static final char SAND = '#';
    private static final char GROUND = '.';

    public static void main(String[] argv) throws Exception {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        int hauteurMax = Integer.parseInt(line.substring(0, line.indexOf(' ')));
        int largeurMax = Integer.parseInt(line.substring(line.indexOf(' ') + 1));

        char[][] chars = new char[hauteurMax][largeurMax];
        int[][] ints = new int[hauteurMax][largeurMax];

        for (int hauteur = 0; hauteur < hauteurMax; hauteur++) {
            line = sc.nextLine();
            for (int largeur = 0; largeur < largeurMax; largeur++) {
                chars[hauteur][largeur] = line.charAt(largeur);
            }
        }

        for (int hauteur = 0; hauteur < hauteurMax; hauteur++) {
            for (int largeur = 0; largeur < largeurMax; largeur++) {
                if (chars[hauteur][largeur] == SAND) {
                    ints[hauteur][largeur] = getDepth(chars, hauteurMax, largeurMax, hauteur, largeur);
                }
            }
        }

        int max = 0;

        for (int i = 0; i < hauteurMax; i++) {
            for (int j = 0; j < largeurMax; j++) {
                if (ints[i][j] > max) {
                    max = ints[i][j];
                }
            }
        }


        System.out.println("" + max);


    /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }

    static class Pair {
        public int hauteur;
        public int largeur;

        public Pair(int hauteur, int largeur) {
            this.hauteur = hauteur;
            this.largeur = largeur;
        }
    }

    private static int getDepth(char[][] chars, int hauteurMax, int largeurMax, int hauteur, int largeur) {
        if (chars[hauteur][largeur] == GROUND) {
            return 0;
        }
        boolean[][] visited = new boolean[hauteurMax][largeurMax];
        int length = 1;

        for (int h = 0; h < hauteurMax; h++) {
            for (int l = 0; l < largeurMax; l++) {
                visited[h][l] = false;
            }
        }

        List<Pair> pairList = new ArrayList<Pair>();
        List<Pair> edgeList = new ArrayList<Pair>();
        pairList.add(new Pair(hauteur, largeur));

        while (pairList.size() > 0) {
            for (Pair pair : pairList) {
                int x = pair.hauteur;
                int y = pair.largeur;
                if (x > 0) {
                    // on teste pour tous les 4 voisins de la case
                    if (test(x - 1, y, hauteurMax, largeurMax, visited, chars, edgeList) ||
                            test(x + 1, y, hauteurMax, largeurMax, visited, chars, edgeList) ||
                            test(x, y - 1, hauteurMax, largeurMax, visited, chars, edgeList) ||
                            test(x, y + 1, hauteurMax, largeurMax, visited, chars, edgeList)) {
                        return length;
                    }
                }
            }
            pairList.clear();
            pairList.addAll(edgeList);
            edgeList.clear();
            length++;
        }

        return length;
    }

    private static boolean test(int hauteur, int largeur, int hauteurMax, int largeurMax, boolean[][] visited, char[][] chars, List<Pair> edgeList) {
        if (hauteur < 0 || largeur < 0 || hauteur > hauteurMax || largeur > largeurMax) {
            // if input are invalid
            return false;
        } else if (visited[hauteur][largeur]) {
            // si déjà visité
            return false;
        } else if (chars[hauteur][largeur] == GROUND) {
            // si c'est du sol
            return true;
        } else {
            // sinon, si c'est du sable, (valide), non visité
            visited[hauteur][largeur] = true; // on le marque comme visité
            edgeList.add(new Pair(hauteur, largeur)); // on ajoute son voisin dans la liste
            return false;
        }
    }

}
