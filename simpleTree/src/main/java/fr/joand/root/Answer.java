package fr.joand.root;

import java.util.*;

public class Answer {
}

class IsoContestAnswer {

    public static class Node {
        String name;
        int level = 1;
        List<Node> deps = new ArrayList<Node>();

        public Node(String name) {
            super();
            this.name = name;
        }

    }

    public static void main(String[] argv) throws Exception {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        Map<String, Node> nodes = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String[] elts = sc.nextLine().split(" ");
            String A = elts[0];
            String B = elts[1];
            Node a = nodes.get(A);
            if (a == null) {
                a = new Node(A);
                nodes.put(A, a);
            }
            Node b = nodes.get(B);
            if (b == null) {
                b = new Node(B);
                nodes.put(B, b);
            }

            a.deps.add(b);
        }

        for (int i = 0; i < N; i++) {
            for (Node a : nodes.values()) {
                for (Node b : a.deps) {
                    a.level = Math.max(a.level, b.level + 1);
                }
            }
        }

        int highestLevel = 0;
        Node highest = null;
        for (Node a : nodes.values()) {
            if (a.level > highestLevel) {
                highest = a;
                highestLevel = a.level;
            }
        }

        System.out.println(highest.level);
    }
}

