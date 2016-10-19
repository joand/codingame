package fr.joand.root;

import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class App {

    public static void main(String args[]) {

        Scanner in = new Scanner(System.in);
        int numberOfHorses = in.nextInt();
        SortedSet<Integer> horses = new TreeSet<>();
        for (int index = 0; index < numberOfHorses; index++) {
            int strength = in.nextInt();
            boolean unique = horses.add(strength);
            if(!unique){
                System.out.println(0);
                return;
            }
        }


        if (horses.size()==1) {
            System.out.println(horses.first());
        } else {
            List<Integer> sortedHorses = new ArrayList<>(horses);
            SortedSet<Integer> diff = new TreeSet<>();
            for (int index = 0; index < sortedHorses.size() - 1; index++) {

                Integer gap = sortedHorses.get(index + 1) - sortedHorses.get(index);


                System.err.println("gap : " + gap);
                diff.add(gap);
            }

            System.out.println(diff.first());
        }

    }
}