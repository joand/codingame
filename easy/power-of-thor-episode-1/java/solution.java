import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 * ---
 * Hint: You can use the debug stream to print initialTX and initialTY, if Thor seems not follow your orders.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int lightX = in.nextInt(); // the X position of the light of power
        int lightY = in.nextInt(); // the Y position of the light of power
        int initialTX = in.nextInt(); // Thor's starting X position
        int initialTY = in.nextInt(); // Thor's starting Y position
        
        int currentX = initialTX;
        int currentY = initialTY;
        
        // game loop
        while (true) {
            int remainingTurns = in.nextInt(); // The remaining amount of turns Thor can move. Do not remove this line.
            
            int offsetX = lightX - currentX;
            int offsetY = lightY - currentY;
            String res = "N"; // default move
            
            if (offsetX > 0) {
                if (offsetY > 0) {
                    res = "SE";
                    currentX++;
                    currentY++;
                }
                else if (offsetY < 0) {
                    res = "NE";
                    currentX++;
                    currentY--;
                }
                else if (offsetY == 0) {
                    res = "E";
                    currentX++;
                }
            }
            else if (offsetX  < 0) {
                if (offsetY > 0) {
                    res = "SW";
                    currentX--;
                    currentY++;
                }
                else if (offsetY < 0) {
                    res = "NW";
                    currentX--;
                    currentY--;
                }
                else if (offsetY == 0) {
                    res = "W";
                    currentX--;
                }
            }
            else if (offsetX == 0) {
                if (offsetY > 0) {
                    res = "S";
                    currentY++;
                }
                else if (offsetY < 0) {
                    res = "N";
                    currentY--;
                }
                // In this case the light and the initial position are the same
                else if (offsetY == 0) {
                    res = "W";
                }
            }
            
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            // A single line providing the move to be made: N NE E SE S SW W or NW
            System.out.println(res);
        }
    }
}