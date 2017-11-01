import java.util.*;
import java.io.*;
import java.math.*;

class Solution {
    private final static int maxInfluences = 10000;
    static int dfs(int curr,LinkedList<Integer>[] graph, int[] memo) {
        if (memo[curr] != 0)
            return memo[curr];
        int maxDepth = 1;
        for (Integer i: graph[curr]) {
            int val = 1 + dfs(i,graph,memo);
            maxDepth = Math.max(maxDepth,val);
        }
        memo[curr] = maxDepth;
        return memo[curr];
    }

    public static void main(String args[]) {
        LinkedList<Integer>[] graph = new LinkedList[maxInfluences];
        Set<Integer> set = new HashSet<Integer>();
        int[] memo = new int[maxInfluences];
        int depth = 1;
        
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of relationships of influence
        
        for (int i = 0; i < graph.length; ++i) {
            graph[i] = new LinkedList<Integer>();
        }
        
        for (int i = 0; i < n; i++) {
            int x = in.nextInt(); // a relationship of influence between two people (x influences y)
            int y = in.nextInt();
            graph[x].add(y);
            set.add(x);
        }
        
        for (Integer i: set) {
            depth = Math.max(depth,dfs(i,graph,memo));
        }

        // The number of people involved in the longest succession of influences
        System.out.println(depth);
    }
}