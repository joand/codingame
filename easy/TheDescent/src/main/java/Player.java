import java.util.*;
import java.io.*;
import java.math.*;


class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        
        // game loop
        while (true)
        {            
        int hmax=0;
        int imax=0;
        for(int i=0; i<8; i++)
        {
             int mountainH = in.nextInt();
            if(hmax<mountainH)
            {
                hmax=mountainH;
                imax=i;
            }

        }
                    System.out.println(imax);
        }
    }
}
