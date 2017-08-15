/*******
 * Read input from System.in
 * Use System.out.println to ouput your result.
 * Use:
 *  IsoContestBase.localEcho( variable)
 * to display variable in a dedicated area.
 * ***/
package fr.joand.root;
import java.util.*;

public class Root {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int nbOfRows = Integer.valueOf(sc.nextLine());
        //IsoContestBase.localEcho("nbOfRows : "+nbOfRows);
        Map<String, Node> allNodes = new HashMap<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] splitedLine = line.split(" ");
            String keyA = splitedLine[0];
            String keyB = splitedLine[1];

            Node nodeA = getNodeOrStoreIt(keyA, allNodes);
            Node nodeB = getNodeOrStoreIt(keyB, allNodes);

            nodeA.addChild(nodeB);
        }

        System.out.println(getMaxHeight(new ArrayList(allNodes.values())));
    }

    static int getMaxHeight(List<Node> nodes) {
        int maxHeight = 0;
        for (Node node : nodes) {
            maxHeight = node.getHeight() > maxHeight ?
                    node.getHeight() : maxHeight;
        }
        return maxHeight;
    }

    static Node getNodeOrStoreIt(String key, Map<String, Node> allNodes) {
        if (allNodes.containsKey(key)) {
            return allNodes.get(key);
        } else {
            Node node = new Node(key);
            allNodes.put(key, node);
            return node;
        }
    }


    void displayAllNodes(Node node) {
        System.out.println(node.getData());
        for (Node child : node.getChildren()) {
            displayAllNodes(child);
        }
    }
}

class Node {
    final String data;
    Node parent;
    List<Node> children = new ArrayList<>();

    /**
     * https://stackoverflow.com/questions/2603692/what-is-the-difference-between-tree-depth-and-height
     */
    int height = 1;

    public Node(String data) {
        this.data = data;
    }

    public void addChild(Node child) {
        this.children.add(child);
        computeHeight();

        child.setParent(this);
    }

    void computeHeight() {
        this.height = 1 + Root.getMaxHeight(this.children);
        if (this.parent != null) {
            this.parent.computeHeight();
        }
    }

    public String getData() {
        return data;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public int getHeight() {
        return height;
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

