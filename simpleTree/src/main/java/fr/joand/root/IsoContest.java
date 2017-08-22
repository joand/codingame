/*******
 * Read input from System.in
 * Use System.out.println to ouput your result.
 * Use:
 * IsoContestBase.localEcho( variable)
 * to display variable in a dedicated area.
 ***/
package fr.joand.root;

import java.util.*;

public class IsoContest {
    static int nbOfRows;

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        nbOfRows = Integer.valueOf(sc.nextLine());
//        IsoContestBase.localEcho("***************************nbOfRows : " + nbOfRows);
        Map<String, Node> allNodes = new HashMap<>();
        for (int index = 0; index < nbOfRows; index++) {
            String line = sc.nextLine();
            String[] splitedLine = line.split(" ");
            String keyA = splitedLine[0];
            String keyB = splitedLine[1];

            Node nodeA = getNodeOrStoreIt(keyA, allNodes);
            Node nodeB = getNodeOrStoreIt(keyB, allNodes);

            addChild(nodeA, nodeB);
        }

        computeAllHeights(allNodes);

        System.out.println(getMaxHeight(allNodes));
        allNodes.clear();
    }

    protected static void computeAllHeights(Map<String, Node> allNodes) {
        for (Node node : allNodes.values()) {
            //*
            if (node.isLeaf()) {
                computeHeight(node);
            }
            /*/
            for (Node child : node.children) {
                node.height = Math.max(node.height, 1 + child.getHeight());
            }
            //*/
        }
    }

    static int getMaxHeight(List<Node> nodes) {
        int maxHeight = 0;
        for (Node node : nodes) {
            maxHeight = Math.max(node.getHeight(), maxHeight);
        }
        //      IsoContestBase.localEcho("maxHeight : " + maxHeight);
        return maxHeight;
    }

    static int getMaxHeight(Map<String, Node> nodes) {
        //computeAllHeights(nodes);

        int maxHeight = 0;
        for (Node node : nodes.values()) {
            //maxHeight = Math.max(node.getHeight(), maxHeight);
            if (node.isRoot()) {
                return node.getHeight();
            }
        }
        //     IsoContestBase.localEcho("FINAL ANSWER maxHeight : " + maxHeight);
        return maxHeight;
    }

    static Node getNodeOrStoreIt(String key, Map<String, Node> allNodes) {
        if (allNodes.containsKey(key)) {
            Node node = allNodes.get(key);
            //       IsoContestBase.localEcho("existing node : " + node.toString());
            return node;
        } else {
            Node node = new Node(key);
            //     IsoContestBase.localEcho("creating node : " + node.toString());
            allNodes.put(key, node);
            return node;
        }
    }

    static void addChild(Node parent, Node child) {
        //     IsoContestBase.localEcho(parent.data + " has a new child : " + child.data);
        parent.children.add(child);
        //computeHeight(parent);
        child.setParent(parent);
    }

    static void computeHeight(Node node) {
        node.height = Math.max(node.height, 1 + IsoContest.getMaxHeight(node.children));
        //    IsoContestBase.localEcho(node.data + " new height : " + node.height);
        //*
        if (node.parent != null) {
            computeHeight(node.parent);
        }
        //*/
    }

}

class Node {
    final String data;
    Node parent = null;
    List<Node> children = new ArrayList<>();

    /**
     * https://stackoverflow.com/questions/2603692/what-is-the-difference-between-tree-depth-and-height
     */
    int height = 1;

    public Node(String data) {
        this.data = data;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    //*
    public boolean isRoot() {
        return parent == null;
    }

    //*/
    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data='" + data + '\'' +
                //", parent=" + (parent != null ? parent.getData() : "null") +
                ", nbOfChildren=" + children.size() +
                ", height=" + height +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(data, node.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}

