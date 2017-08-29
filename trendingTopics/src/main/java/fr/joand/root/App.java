package fr.joand.root;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] argv) throws Exception {
        final int limit = 40;
        final int window = 60;

        Scanner sc = new Scanner(System.in);
        int nbOfHashtags = Integer.parseInt(sc.nextLine());

        List<String> allHashtags = new ArrayList<>();
        while (sc.hasNextLine()) {
            String hashtag = sc.nextLine();
            allHashtags.add(hashtag);
        }

        for (int outterIndex = 0; outterIndex < nbOfHashtags; outterIndex++) {
            int occurence = 0;
            String reference = allHashtags.get(outterIndex);
            int max = Math.min(nbOfHashtags, outterIndex + window);
            for (int innerIndex = outterIndex; innerIndex < max; innerIndex++) {
                String current = allHashtags.get(innerIndex);
                if (current.equals(reference)) {
                    occurence++;
                }
            }
            if (occurence >= limit) {
                System.out.println(reference);
                return;
            }
        }

        System.out.println("Pas de trending topic");
    }
}

