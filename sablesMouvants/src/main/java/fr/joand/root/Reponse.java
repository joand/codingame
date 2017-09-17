package fr.joand.root;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reponse {
    private static final char SAND = '#';
    private static final char GROUND = '.';

    public static void main( String[] argv ) throws Exception {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        int h = Integer.parseInt(line.substring(0, line.indexOf(' ')));
        int l = Integer.parseInt(line.substring(line.indexOf(' ') + 1));

        char[][] chars = new char[h][l];
        int[][] ints = new int[h][l];

        for (int i = 0; i < h; i++) {
            line = sc.nextLine();
            for (int j = 0; j < l; j++) {
                chars[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < l; j++) {
                if(chars[i][j] == SAND){
                    ints[i][j] = getDepth(chars, h, l,  i, j);
                }
            }
        }

        int max = 0;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < l; j++) {
                if(ints[i][j] > max){
                    max = ints[i][j];
                }
            }
        }



        System.out.println("" + max);


    /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }

    static class Pair{
        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int getDepth(char[][] chars, int h, int l, int hx, int lx) {
        if(chars[hx][lx] == GROUND) return 0;
        boolean[][] visited = new boolean[h][l];
        int length = 1;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < l; j++) {
                visited[i][j] = false;
            }
        }

        List<Pair> pairList = new ArrayList<Pair>();
        List<Pair> edgeList = new ArrayList<Pair>();
        pairList.add(new Pair(hx, lx));

        while (pairList.size() > 0){
            for(Pair pair : pairList){
                int x = pair.x;
                int y = pair.y;
                if(x > 0){
                    if(test(x-1, y, h, l, visited, chars, edgeList) ||
                            test(x+1, y, h, l, visited, chars, edgeList) ||
                            test(x, y-1, h, l, visited, chars, edgeList) ||
                            test(x, y+1, h, l, visited, chars, edgeList)){
                        return length;
                    }
                }
            }
            pairList.clear();
            pairList.addAll(edgeList);
            edgeList.clear();
            length++;
        }

        return length;
    }

    private static boolean test(int x, int y, int h, int l, boolean[][] visited, char[][] chars, List<Pair> edgeList) {
        if(x < 0 || y < 0 || x > h || y > l){
            return false;
        } else if(visited[x][y]){
            return false;
        } else if(chars[x][y] == GROUND){
            return true;
        } else {
            visited[x][y] = true;
            edgeList.add(new Pair(x, y));
            return false;
        }
    }

}
