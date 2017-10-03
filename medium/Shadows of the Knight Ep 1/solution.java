import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // width of the building.
        int H = in.nextInt(); // height of the building.
        int N = in.nextInt(); // maximum number of turns before game over.
        int x0 = in.nextInt();
        int y0 = in.nextInt();
        
        int x1 = 0;
        int y1 = 0;
        int x2 = W - 1;
        int y2 = H - 1;
        
        // game loop
        while (true) {
            String bombDir = in.next(); // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)

            if (bombDir.contains("U")) {
                y2 = y0 - 1;
            }
            
            else if (bombDir.contains("D")) {
                y1 = y0 + 1;
            }
            
            if (bombDir.contains("L")) {
                x2 = x0 - 1;
            }
            
            else if (bombDir.contains("R")) {
                x1 = x0 + 1;
            }
            
            x0 = x1 + (x2 - x1) / 2;
            y0 = y1 + (y2 - y1) / 2;

            // the location of the next window Batman should jump to.
            System.out.println(x0 + " " + y0);
        }
    }
}
