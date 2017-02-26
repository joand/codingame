package fr.joand;

import fr.joand.exception.ElementNotFoundException;
import fr.joand.model.Edge;
import fr.joand.model.Factory;
import fr.joand.model.Owner;
import fr.joand.model.Troop;

import java.util.*;

/**
 * sources :
 * http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html
 * https://mail-attachment.googleusercontent.com/attachment/u/0/?ui=2&ik=26879a90be&view=att&th=13a50271fc085591&attid=0.2&disp=inline&realattid=f_h85xz9341&safe=1&zw&saddbat=ANGjdJ80ygnCoNeDC9IW_BeMrbaHsPMOyCZVHVhJiRIb3Bx7zYzvotbkSu4Ccl7L0bQ1g5MU-3qVVpz8J1r1gDKNr8XmsVwUn2OFyRsKXsmPmN5ByoMPdpR6J6jnoAKCBl0NXk3N3z-z_JNVARkc-Hx5ES9qBL5FA-ZKaTmuzSeKpKxmNSdFFFnv2iKayFpJbUzhB5imQxy4e9jxrpdAxvt7ae91iAwECj5g2gu0ESl05x9Mrb2ydh-gwlMsZq8xyilMa4eAQnXVjdYsQSbQ3KhNgEozfBzXqxRo7TjHNK_ok2Ar0Qy9aPtoLFTdwh4T74kZ-yDJJiul1mPTobasPWv1oKBW76oaOybAqmnkbdbwkcgqMUgYs8r0iF3xsXfOavLfhJsnWi8ElxvrIMpgEsbMsyV9UXRGm3C-wG0QqNWfwVeLanr5ZcIIBlOs1LDY9xYQ-Vs0Xco1QfkCQydWI_vjGsbAAYOc2NtYkwHaayAx4bE12TuTOe1jX3mggpXKLhMoDixeomO4AUrTcX9XFP2UAUl96bBX-sbQmpZQ7Aqy9Q2f4MZZUc4LTksPgL9i5JtNsi6xSRH-GK9BC8RDjz8iih51LSkYfEj2aS_W9sOOdNiztWw7E3i0hyRld-f2bHxxIULepnkirx1aa0rzANR15JE7rLh5aopgb30-rQ
 * http://algs4.cs.princeton.edu/code/
 * https://mail-attachment.googleusercontent.com/attachment/u/0/?ui=2&ik=26879a90be&view=att&th=144181d1dbd12052&attid=0.1&disp=inline&realattid=f_hrgp4bvu0&safe=1&zw&saddbat=ANGjdJ_JIBSxUhjXUIqeJ0jzNlA-BHjIGbRoerYHZzzbM2NZW4qF3Gpz2UWsx-X0yr39Od8wJPHyPZ2VaL3zEdwzALacEgmHfOYsWz1cQqq2efevk65cT4oxD2xHMb9oyHkHy0IrCoBH_R18XRC08fj6Q-4_D0Fej7Scy1FwLL8l3UJYdTWMtG4cFghJIsFJGxF-FiZurJ7Yc_3ZEj5dA1-KxoJe2pEufyZoDR93C6-VjA-Iw6D7iJfLsh8r4MO1v-dlwQbut9TWVM-ccv5hjxkaT1N96mM90DUiUQV4849iKRbG2x_4mxuLAEGqMizeh7eaTrfZtL8MYqTI-dDQ53i9tbfCesyTExnZyUmDlD_Vxnkq8Sl5NXwxf-VBznUoC-248OsGl43SesmR8Ck5H49iLEG6_h4wrP9jZvPNOK2qYzmmwIVjfimdnPrNOY331mLhXPGvQ_UsTUJ5AC4L2z5IdVGnrwa-moX91ib6znpu7TNmf4yheAZeZiAixXKxmQYNKXlsuiDlYJFOlRGrgglk-y6hOqLIfXwYxGrY8zi7lxTS3Ggr-1nOUox14WwoCftRhiJ86XtwG1vNFC7ll6eO9qgIrDbkJQ7-tf1BTHsgxwf1NVXLw3-qUjJm4QA1RQerQkJnGhXw3mELyCvNrZ3V2QabCsHvEEuBlVAE8w
 * https://www.khanacademy.org/computing/computer-science/algorithms
 */
public class App {

    /**
     * @return the full Edge or null if not found
     */
    static Edge getEdge(List<Edge> edges, Edge partialEdgeToFind) {
        return edges.stream()
                .filter(edge -> edge.equals(partialEdgeToFind))
                .findAny().orElseThrow(ElementNotFoundException::new);
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int factoryCount = in.nextInt(); // the number of factories
        Map<Integer, Factory> factories = new HashMap<>(factoryCount);

        int linkCount = in.nextInt(); // the number of links between factories
        List<Edge> edges = new ArrayList<>(linkCount);
        for (int i = 0; i < linkCount; i++) {
            int factory1 = in.nextInt();
            int factory2 = in.nextInt();
            int distance = in.nextInt();

            Edge edge = new Edge(factory1, factory2, distance);
            edges.add(edge);
        }

        // game loop
        while (true) {
            int entityCount = in.nextInt(); // the number of entities (e.g. factories and troops)
            for (int i = 0; i < entityCount; i++) {
                int entityId = in.nextInt();
                String entityType = in.next();
                int arg1 = in.nextInt();
                Owner owner = Owner.get(arg1);

                int arg2 = in.nextInt();
                int arg3 = in.nextInt();
                int arg4 = in.nextInt();
                int arg5 = in.nextInt();
                switch (entityType) {
                    case "FACTORY":
                        int nbOfCyborgsInFactory = arg2;
                        int production = arg3;
                        if(factories.containsKey(entityId)){
                            Factory factory = factories.get(entityId);
                            factory.setOwner(owner);
                            factory.setStockOfCyborgs(nbOfCyborgsInFactory);
                            factory.setProduction(production);
                        } else {
                            Factory factory = new Factory(entityId, owner, nbOfCyborgsInFactory, production);
                            factories.put(entityId, factory);
                        }
                        break;
                    case "TROOP":
                        int factoryIdSource = arg2;
                        int factoryIdDestination = arg3;
                        Edge edgeTmp = new Edge(factoryIdSource, factoryIdDestination);
                        Edge edge = getEdge(edges, edgeTmp);

                        int nbOfCyborgsInTroop = arg4;
                        int remainingDistance = arg5;

                        Troop troop = new Troop(entityId, owner, nbOfCyborgsInTroop, remainingDistance, factoryIdDestination);
                        edge.addTroop(troop);
                        break;
                    default:
                        break;
                }

            }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            // Any valid action, such as "WAIT" or "MOVE source destination cyborgs"
            System.out.println("WAIT");
        }
    }
}
