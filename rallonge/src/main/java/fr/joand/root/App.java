package fr.joand.root;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] argv) throws Exception {

        Scanner sc = new Scanner(System.in);
        int nbRallonges = Integer.parseInt(sc.nextLine());

        List<Rallonge> mf = new ArrayList<>();
        List<Rallonge> ff = new ArrayList<>();
        List<Rallonge> mm = new ArrayList<>();

        while (sc.hasNextLine()) {
            String[] input = sc.nextLine().split(" ");
            Rallonge rallonge = new Rallonge(input[0], Integer.parseInt(input[1]));
            if (rallonge.getType().equals("F-M") || rallonge.getType().equals("M-F")) {
                mf.add(rallonge);
            } else if (rallonge.getType().equals("F-F")) {
                ff.add(rallonge);
            } else if (rallonge.getType().equals("M-M")) {
                mm.add(rallonge);
            }
        }

        int resultat = 0;
        for (Rallonge rallonge : mf) {
            resultat += rallonge.getLength();
        }

        ff = ff.stream().sorted((o1, o2) -> o2.getLength() - o1.getLength())
                .collect(Collectors.toList());
        mm = mm.stream().sorted((o1, o2) -> o2.getLength() - o1.getLength())
                .collect(Collectors.toList());

        int nb = Math.min(ff.size(), mm.size());
        for (int i = 0; i < nb; i++) {
            resultat += mm.get(i).getLength();
            resultat += ff.get(i).getLength();
        }

        System.out.println(resultat);
    }
}

class Rallonge {
    String type;
    int length;

    public Rallonge(String type, int length) {
        this.type = type;
        this.length = length;
    }

    public String getType() {
        return type;
    }

    public int getLength() {
        return length;
    }
}