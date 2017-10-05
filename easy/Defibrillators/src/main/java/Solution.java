import java.util.*;
import java.io.*;
import java.math.*;


class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String LON = in.next();
        String LAT = in.next();
        double lon = Math.toRadians(Double.parseDouble(LON.replaceAll(",",".")));
        double lat = Math.toRadians(Double.parseDouble(LAT.replaceAll(",",".")));
        int N = in.nextInt();
        in.nextLine();
        String[] DEFIB = new String[N];
        String[][] prts = new String[N][6];
        double[] y = new double[N];
        double[] x = new double[N];
        double d;
        for (int i = 0; i < N; i++) {
            DEFIB[i] = in.nextLine();
            String[] parts = DEFIB[i].split(";");
            for(int j=0; j<6; j++)
            {
               prts[i][j] = parts[j];
            }
        }
        for(int i=0;i<N;i++)
        {
            x[i] = Math.toRadians(Double.parseDouble(prts[i][4].replaceAll(",",".")));
            y[i] = Math.toRadians(Double.parseDouble(prts[i][5].replaceAll(",",".")));
        }
        double temp,xx,yy;
        temp =1000000000;
        int odp=0;
        for(int i=0; i<N; i++)
        {
            xx=(x[i]-lon)*Math.cos((lat+y[i])/2);
            yy=lat-y[i];
            d=Math.sqrt(Math.pow(xx,2)+Math.pow(yy,2))*6371;
            if(temp > d) { odp = i;temp = d; //System.out.println("Aktualny temp "+temp);
            }


        }
        
                System.out.println(prts[odp][1]);
    }
}
