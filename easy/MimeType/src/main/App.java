import java.util.*;
import java.io.*;
import java.math.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // Number of elements which make up the association table.
        int Q = in.nextInt(); // Number Q of file names to be analyzed.
        in.nextLine();
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String EXT = in.next();
            String MT = in.next();
            map.put(EXT.toLowerCase(), MT);
            in.nextLine();
        }
             
        String exten="";
        for(int i=0; i<Q; i++)
        {
            String FNAME = in.nextLine().toLowerCase();
            int pos = FNAME.lastIndexOf('.');
          exten = (pos == -1) ? "" : FNAME.substring(pos+1, FNAME.length());
         if(exten.length()>10) System.out.println("UNKNOWN");
            if(map.containsKey(exten)) System.out.println(map.get(exten));
            else System.out.println("UNKNOWN");
        }
        

    }
}
