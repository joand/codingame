package fr.joand.root;

import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int numberOfTelephoneNb = in.nextInt();
        Map<Character, Node<Character>> root = new HashMap<>();

        int answer = 0;
        for (int i = 0; i < numberOfTelephoneNb; i++) {
            String telephone = in.next();
            char firstChar = telephone.charAt(0);
            Node<Character> node;
            if (root.containsKey(firstChar)) {
                node = root.get(firstChar);
            } else {
                node = new Node<>(firstChar);
                answer++;
                root.put(firstChar, node);
            }
            char[] phoneNb = telephone.toCharArray();
            for (int j = 1; j < phoneNb.length; j++) {
                Node<Character> child = new Node<>(phoneNb[j]);
                if(node.containsChild(child)){

                } else {
                    node.addChild(child);
                    answer++;
                }
                node = node.getChild(child);
            }

        }

        // To debug: System.err.println("Debug messages...");
        System.out.println(answer);
    }
}


class Node<T> {
    T data;
    private Node<T> parent;
    private List<Node<T>> children = new ArrayList<>();

    public Node(T data) {
        this.data = data;
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

    public void addChild(Node<T> child) {
        children.add(child);
    }

    public boolean containsChild(Node<T> child){
        return getChild(child) != null;
    }


    public Node<T> getChild(Node<T> child){
        for(Node<T> current : children){
            if(current.getData().equals(child.getData())){
                return current;
            }
        }
        return null;
    }
}