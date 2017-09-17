package fr.joand.root;

import java.util.List;
import java.util.Scanner;

/**
 * utiliser, au choix :
 * Dijkstra
 * For a given source vertex (node) in the graph, the algorithm finds the path with lowest cost (i.e. the shortest path) between that vertex and every other vertex.   It can also be used for finding costs of shortest paths from a single vertex to a single destination vertex by stopping the algorithm once the shortest path to the destination vertex has been determined.
 * https://rosettacode.org/wiki/Dijkstra%27s_algorithm#Java
 * http://blog.cleancoder.com/uncle-bob/2016/10/26/DijkstrasAlg.html
 * https://github.com/mburst/dijkstras-algorithm/blob/master/Dijkstras.java
 * <p>
 * Floyd–Warshall (https://cs.stackexchange.com/questions/26344/floyd-warshall-algorithm-on-undirected-graph)
 * http://algs4.cs.princeton.edu/44sp/FloydWarshall.java.html
 */
public class App {
    public static void main(String[] argv) throws Exception {

        Scanner sc = new Scanner(System.in);

        String[] dimension = sc.nextLine().split(" ");
        int hauteurTotale = Integer.parseInt(dimension[0]);
        int largeurTotale = Integer.parseInt(dimension[1]);

        final char terre = '.';
        final char sable = '#';

        char[][] desert = new char[hauteurTotale][largeurTotale];

        for (int hauteur = 0; hauteur < hauteurTotale; hauteur++) {

            String line = sc.nextLine();
            desert[hauteur] = line.toCharArray();
            /* Lisez les données et effectuez votre traitement */
        }
    /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }

    List<Edge> convert(char[][] map) {
        // todo
        return null;
    }
}

class Edge {
    final String from;
    final String to;

    final String length;

    public Edge(String from, String to, String length) {
        this.from = from;
        this.to = to;
        this.length = length;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getLength() {
        return length;
    }

    public int getIntLength() {
        return Integer.parseInt(length);
    }

    public float getFloatLength() {
        return Float.parseFloat(length);
    }
}