import java.util.*;

class Player {
    
    private static class Graph {
        
        LinkedList<Integer>[] graph;
        
        public Graph(int n) {
            graph = new LinkedList[n];
            for (int i = 0; i < n; ++i) {
                   graph[i] = new LinkedList<Integer>();
            }
        }
        
        public void link(int index1, int index2) { //creates links bwtween 2 nodes
            graph[index1].add(index2);
            graph[index2].add(index1);
        }
        
        public void cut(int index1, int index2) { // cuts links bbtween 2 nodes
            graph[index1].remove(Integer.valueOf(index2));
            graph[index2].remove(Integer.valueOf(index1));
        }
        
        public LinkedList<Integer> neighbors(int index) { // returns list of nodes adjacent to index
            return graph[index];   
        }
        
        
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // the total number of nodes in the level, including the gateways
        int L = in.nextInt(); // the number of links
        int E = in.nextInt(); // the number of exit gateways
        
        Graph graph = new Graph(N);
        
        Set<Integer> exits = new HashSet<Integer>(); // stores indices of exit points
        for (int i = 0; i < L; i++) {
            int N1 = in.nextInt(); // N1 and N2 defines a link between these nodes
            int N2 = in.nextInt();
            graph.link(N1,N2);
        }
        for (int i = 0; i < E; i++) {
            int EI = in.nextInt(); // the index of a gateway node
            exits.add(EI);
        }
        
        Queue<Integer> q; // queue needed to perfrom breadth-first search
        boolean foundExit; // check if exit is found during traversal
        // game loop
        while (true) {
            int SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn
            foundExit = false;
            q = new LinkedList<Integer>();
            q.offer(SI);
            
            while (!q.isEmpty() && !foundExit) {
                int curr = q.poll();
                for (int i: graph.neighbors(curr)) {
                    if (exits.contains(i)) { // an exit point has been found
                        System.out.println(curr + " " + i); 
                        graph.cut(curr,i); // / Cut link between exit and last node on path from Skynet agent
                        foundExit = true;
                        break;
                    } else {
                        q.offer(i);   
                    }
                }
            }
           
        }
       
    }
}