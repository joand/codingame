package fr.joand.root;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 30/10/2016.
 */
public class Node<T> {
    private T data;
    private Node<T> parent;
    private List<Node<T>> children = new ArrayList<>();

    public Node(T data) {
        this.data = data;
    }

    public void addChild(Node<T> child){
        children.add(child);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public List<Node<T>> getChildren() {
        return children;
    }

}
