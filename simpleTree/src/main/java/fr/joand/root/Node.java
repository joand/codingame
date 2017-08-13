package fr.joand.root;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by user on 30/10/2016.
 */
public class Node {
    private final String data;
    private Node parent;
    private List<Node> children = new ArrayList<>();

    /**
     * https://stackoverflow.com/questions/2603692/what-is-the-difference-between-tree-depth-and-height
     */
    private int height = 1;

    public Node(String data) {
        this.data = data;
    }

    public void addChild(Node child) {
        children.add(child);
        computeHeight();

        child.setParent(this);
        if (parent != null) {
            parent.computeHeight();
        }
    }

    private void computeHeight() {
        height = 1 + Root.getMaxHeight(children);
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
