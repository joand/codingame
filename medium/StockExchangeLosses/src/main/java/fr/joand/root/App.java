package fr.joand.root;

import java.util.Scanner;

public class App {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int nbOfStockValues = in.nextInt();
        in.nextLine();
        String stockValues = in.nextLine();
        System.err.println("stockValues : " + stockValues);


        String[] strStockValue = stockValues.split(" ");
        int[] stockValue = new int[nbOfStockValues];

        for (int index = 0; index < nbOfStockValues; index++) {
            stockValue[index] = Integer.valueOf(strStockValue[index]);
        }

        int loss = 0;
        int largestLoss = 0;

        for (int index = 0; index < nbOfStockValues - 1; index++) {
            int variation = stockValue[index + 1] - stockValue[index];
            if (loss + variation < 0) {
                loss = loss + variation;
                if (loss < largestLoss) {
                    largestLoss = loss;
                }
            } else {
                if (loss < largestLoss) {
                    largestLoss = loss;
                }
                loss = 0;
            }
        }

        System.out.println(largestLoss);
    }
}
