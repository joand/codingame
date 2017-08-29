package fr.joand.root;

import java.util.ArrayList;
import java.util.Scanner;

public class Answser {
    public static void main(String[] argv) throws Exception {
        String line;
        Scanner sc = new Scanner(System.in);
        ArrayList<String> myzone = new ArrayList<String>();
        ArrayList<String> doublon = new ArrayList<String>();

        int doublonC = 0;
        int horszone = 0;
        int telinvalide = 0;

        int count = Integer.parseInt(sc.nextLine());
        int i = 0;
        line = sc.nextLine();
        while (i < line.split(";").length) {
            myzone.add(line.split(";")[i++].split("<")[0]);
        }
        i = 0;
        while (i < count) {
            line = sc.nextLine();
            int index = 0;
            int y;
            y = 0;
            while (y++ < 3)
                index = line.indexOf(';', index + 1);
            if (doublon.contains(line.substring(0, index))) {
                doublonC++;
            } else {
                doublon.add(line.substring(0, index));
                y = 0;
                index = 0;
                if (!myzone.contains(line.split(";")[4].split("<")[0]))
                    horszone++;

                if (!telvalide(line.split(";")[3])) {
                    telinvalide++;
                }
            }
            i++;
            /* Lisez les données et effectuez votre traitement */
        }
        System.out.println(doublonC + " " + telinvalide + " " + horszone);
    }

    private static boolean telvalide(String substring) {
        if (substring.charAt(0) != '+')
            return false;
        int i = 1;
        while (i < 4) {
            if (substring.charAt(i) == '-')
                break;
            if (substring.charAt(i) > '9' || substring.charAt(i) < '0')
                return false;
            i++;
        }
        if (i == 1 || substring.charAt(i) != '-')
            return false;
        int y = 0;
        i++;
        while (i < substring.length()) {
            if (substring.charAt(i) > '9' || substring.charAt(i) < '0')
                return false;
            i++;
            if (y++ > 11)
                return false;
        }
        return (y > 7);
    }


}
