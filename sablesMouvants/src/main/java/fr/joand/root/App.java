package fr.joand.root;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] argv) throws Exception {

        Scanner scanner = new Scanner(System.in);

        String[] dimensions = scanner.nextLine().split(" ");
        final int hauteurTotale = Integer.parseInt(dimensions[0]);
        final int largeurTotale = Integer.parseInt(dimensions[1]);

        final char TERRE = '.';
        final char SABLE = '#';

        char[][] desert = new char[hauteurTotale][largeurTotale];
        String[][] names = new String[hauteurTotale][largeurTotale];
        int heatMap[][] = new int[hauteurTotale][largeurTotale];

        // init maps
        Integer nomDesCases = 0;
        for (int hauteur = 0; hauteur < hauteurTotale; hauteur++) {
            String line = scanner.nextLine();
            for (int largeur = 0; largeur < largeurTotale; largeur++) {
                desert[hauteur][largeur] = line.charAt(largeur);
                names[hauteur][largeur] = nomDesCases.toString();
                nomDesCases++;
            }
        }

        // init edges/graph
        List<String> edges = new ArrayList<>();
        for (int hauteur = 0; hauteur < hauteurTotale; hauteur++) {
            for (int largeur = 0; largeur < largeurTotale; largeur++) {
                String start = names[hauteur][largeur];

                // format : "start end distance"
                if (hauteur + 1 < hauteurTotale) {
                    String edgeA = start + " " + names[hauteur + 1][largeur] + " " + 1;
                    edges.add(edgeA);
                }

                if (hauteur - 1 > 0) {
                    String edgeB = start + " " + names[hauteur - 1][largeur] + " " + 1;
                    edges.add(edgeB);
                }

                if (largeur + 1 < largeurTotale) {
                    String edgeC = start + " " + names[hauteur][largeur + 1] + " " + 1;
                    edges.add(edgeC);
                }

                if (largeur - 1 > 0) {
                    String edgeD = start + " " + names[hauteur][largeur - 1] + " " + 1;
                    edges.add(edgeD);
                }
            }
        }

        // do the job

        for (int hauteurSable = 0; hauteurSable < hauteurTotale; hauteurSable++) {
            for (int largeurSable = 0; largeurSable < largeurTotale; largeurSable++) {
                heatMap[hauteurSable][largeurSable] = 0;

                if (desert[hauteurSable][largeurSable] == SABLE) { // pour chaque carré de sable
                    String debut = names[hauteurSable][largeurSable];
                    int deplacementMin = Integer.MAX_VALUE;
                    for (int hauteurTerre = 0; hauteurTerre < hauteurTotale; hauteurTerre++) {
                        for (int largeurTerre = 0; largeurTerre < largeurTotale; largeurTerre++) {
                            if (desert[hauteurTerre][largeurTerre] == TERRE) { // si c'est de la terre
                                String fin = names[hauteurTerre][largeurTerre];
                                boolean isDirectedGraph = false;
                                PathFinder_ pf = PathFinder_.makePathFinder(edges, debut, fin, isDirectedGraph);
                                int deplacement = pf.getLength();
                                deplacementMin = Math.min(deplacement, deplacementMin);
                            }
                        }
                    }
                    heatMap[hauteurSable][largeurSable] = deplacementMin;
                }
            }
        }

        // find the max
        int answer = 0;
        for (int hauteur = 0; hauteur < hauteurTotale; hauteur++) {
            for (int largeur = 0; largeur < largeurTotale; largeur++) {
                answer = Math.max(answer, heatMap[hauteur][largeur]);
            }
        }

        System.out.println(answer);

    }

}

class PathFinder_ {
    private List<Edge> edges = new ArrayList<>();
    private Set<String> nodeNames = new HashSet<>();
    private Map<String, Node> nodes = new HashMap<>();
    private Node endNode;
    private boolean isDirectedGraph;

    private PathFinder_() {
    }

    // todo : to customize
    public static PathFinder_ makePathFinder(List<String> edges, String debut, String fin, boolean isDirectedGraph) {
        PathFinder_ pf = new PathFinder_();
        pf.isDirectedGraph = isDirectedGraph;

        for (String edge : edges) {
            String[] data = edge.split(" ");

            String start = data[0];
            String end = data[1];
            int length = Integer.parseInt(data[2]);

            pf.addEdge(start, end, length);
        }
        pf.findPath(debut, fin);
        return pf;
    }

    // todo : to customize
    public static PathFinder_ makePathFinder(String graph, String debut, String fin, boolean isDirectedGraph) {
        PathFinder_ pf = new PathFinder_();
        pf.isDirectedGraph = isDirectedGraph;
        Pattern edgePattern =
                Pattern.compile("(\\D+)(\\d+)(\\D+)");
        String[] edges = graph.split(",");
        for (String edge : edges) {
            Matcher matcher = edgePattern.matcher(edge);
            if (matcher.matches()) {
                String start = matcher.group(1);
                int length = Integer.parseInt(matcher.group(2));
                String end = matcher.group(3);
                pf.addEdge(start, end, length);
            }
        }
        pf.findPath(debut, fin);
        return pf;
    }

    public void findPath(String begin, String end) {
        List<String> unvisited = initializeSearch(begin, end);

        for (String node = begin;
             node != null && !node.equals(end);
             node = getNext(unvisited)) {
            unvisited.remove(node);
            visit(node);
        }

        setupEndNode(end);
    }

    private List<String> initializeSearch(String begin,
                                          String end) {
        nodeNames.add(begin);
        nodeNames.add(end);
        List<String> unvisited = new ArrayList<>(nodeNames);
        for (String node : unvisited) {
            nodes.put(node, new Node(Integer.MAX_VALUE));
        }
        nodes.get(begin).length = 0;
        return unvisited;
    }

    private void visit(String node) {
        List<Edge> neighbors = findEdges(node);
        Node curNode = nodes.get(node);
        for (Edge e : neighbors) {
            Node nbr = nodes.get(e.end);

            int newLength = curNode.length + e.length;
            if (nbr.length > newLength) {
                nbr.length = newLength;
                nbr.path = new ArrayList<>();
                nbr.path.addAll(curNode.path);
                nbr.path.add(node);
            }
        }
    }

    private void setupEndNode(String end) {
        endNode = nodes.get(end);
        if (endNode.length != Integer.MAX_VALUE) {
            endNode.path.add(end);
        } else {
            endNode.length = 0;
        }
    }

    private String getNext(List<String> unvisited) {
        String minNodeName = null;
        int minLength = Integer.MAX_VALUE;

        for (String name : unvisited) {
            Node candidate = nodes.get(name);
            if (candidate.length < minLength) {
                minLength = candidate.length;
                minNodeName = name;
            }
        }
        return minNodeName;
    }

    private List<Edge> findEdges(String begin) {
        List<Edge> found = new ArrayList<>();
        for (Edge e : edges) {
            if (e.begin.equals(begin)) {
                found.add(e);
            }
        }
        return found;
    }

    public int getLength() {
        return endNode.length;
    }

    public List<String> getPath() {
        return endNode.path;
    }

    public void addEdge(String start, String end, int length) {
        edges.add(new Edge(start, end, length));
        if (!isDirectedGraph) {
            edges.add(new Edge(end, start, length));
        }
        nodeNames.add(start);
        nodeNames.add(end);
    }

    private static class Edge {
        public final String begin;
        public final String end;
        public final int length;

        public Edge(String begin, String end, int length) {
            this.begin = begin;
            this.end = end;
            this.length = length;
        }
    }

    private static class Node {
        public int length;
        public List<String> path;

        public Node(int l) {
            this.length = l;
            this.path = new ArrayList<>();
        }
    }
}