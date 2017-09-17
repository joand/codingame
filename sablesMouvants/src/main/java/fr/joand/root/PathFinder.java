package fr.joand.root;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Robert C. Martin
 * http://blog.cleancoder.com/uncle-bob/2016/10/26/DijkstrasAlg.html
 */
public class PathFinder {
    private List<Edge> edges = new ArrayList<>();
    private Set<String> nodeNames = new HashSet<>();
    private Map<String, Node> nodes = new HashMap<>();
    private Node endNode;
    private boolean isDirectedGraph;

    private PathFinder() {
    }

    // todo : to customize
    public static PathFinder makePathFinder(String graph, String debut, String fin, boolean isDirectedGraph) {
        PathFinder pf = new PathFinder();
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