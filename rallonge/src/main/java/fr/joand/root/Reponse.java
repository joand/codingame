package fr.joand.root;

import java.util.*;

public class Reponse {
    public static void main(String[] argv) throws Exception {
        String line;
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        ArrayList<Integer> mf = new ArrayList<Integer>();
        ArrayList<Integer> mm = new ArrayList<Integer>();
        ArrayList<Integer> ff = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            line = sc.nextLine();
            StringTokenizer token = new StringTokenizer(line);
            String type = token.nextToken();
            int length = Integer.parseInt(token.nextToken());
            if (type.equals("M-F") || type.equals("F-M"))
                mf.add(length);
            if (type.equals("M-M"))
                mm.add(length);
            if (type.equals("F-F"))
                ff.add(length);
        }

        int result = 0;
        for (Iterator iterator = mf.iterator(); iterator.hasNext(); ) {
            Integer integer = (Integer) iterator.next();
            result += integer;
        }

        int nbToProcess = Math.min(mm.size(), ff.size());
        Collections.sort(mm);
        Collections.sort(ff);

        for (int i = 1; i <= nbToProcess; i++) {
            result += mm.get(mm.size() - i);
            result += ff.get(ff.size() - i);
        }

        System.out.println(result);

    }
}
