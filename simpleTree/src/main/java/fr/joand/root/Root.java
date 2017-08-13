package fr.joand.root;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Root {

    public static void main(String[] args) {

        String keyA = "A";
        String keyB = "B";

        Map<String, Node> allNodes = new HashMap<>();

        Node nodeA = getNodeOrStoreIt(keyA, allNodes);
        Node nodeB = getNodeOrStoreIt(keyB, allNodes);

        nodeA.addChild(nodeB);

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
