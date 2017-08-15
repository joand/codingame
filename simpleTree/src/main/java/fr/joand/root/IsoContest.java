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

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int nbOfRows = Integer.valueOf(sc.nextLine());
        System.out.println("***************************nbOfRows : " + nbOfRows);
        Map<String, Node> allNodes = new HashMap<>();
        for (int index = 0; index < nbOfRows; index++) {
            String line = sc.nextLine();
            String[] splitedLine = line.split(" ");
            String keyA = splitedLine[0];
            String keyB = splitedLine[1];

            Node nodeA = getNodeOrStoreIt(keyA, allNodes);
            Node nodeB = getNodeOrStoreIt(keyB, allNodes);

            nodeA.addChild(nodeB);
        }

        System.out.println(getMaxHeight(allNodes));
        allNodes.clear();
    }

    static int getMaxHeight(List<Node> nodes) {
        int maxHeight = 0;
        for (Node node : nodes) {
            maxHeight = node.getHeight() > maxHeight ?
                    node.getHeight() : maxHeight;
        }
        System.out.println("maxHeight : " + maxHeight);
        return maxHeight;
    }

    static int getMaxHeight(Map<String, Node> nodes) {
        int maxHeight = 0;
        for (Node node : nodes.values()) {
            maxHeight = node.getHeight() > maxHeight ?
                    node.getHeight() : maxHeight;
        }
        System.out.println("FINAL ANSWER maxHeight : " + maxHeight);
        return maxHeight;
    }

    static Node getNodeOrStoreIt(String key, Map<String, Node> allNodes) {
        if (allNodes.containsKey(key)) {
            Node node = allNodes.get(key);
            System.out.println("existing node : " + node.toString());
            return node;
        } else {
            Node node = new Node(key);
            System.out.println("creating node : " + node.toString());
            allNodes.put(key, node);
            return node;
        }
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

    public void addChild(Node child) {
        System.out.println(this.data + " has a new child : " + child.data);
        this.children.add(child);
        computeHeight();
        child.setParent(this);
    }

    void computeHeight() {
        this.height = 1 + IsoContest.getMaxHeight(this.children);
        System.out.println(this.data + " new height : " + this.height);
        if (this.parent != null) {
            this.parent.computeHeight();
        }
    }

    public String getData() {
        return data;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public boolean isRoot() {
        return parent == null;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data='" + data + '\'' +
                ", parent=" + (parent != null ? parent.getData() : "null") +
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

