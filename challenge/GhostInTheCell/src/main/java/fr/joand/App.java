package fr.joand;

import fr.joand.exception.ElementNotFoundException;
import fr.joand.model.Edge;
import fr.joand.model.Factory;
import fr.joand.model.Owner;
import fr.joand.model.Troop;

import java.util.*;
import java.util.stream.Collectors;

/**
 * sources :
 * http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html
 * https://mail-attachment.googleusercontent.com/attachment/u/0/?ui=2&ik=26879a90be&view=att&th=13a50271fc085591&attid=0.2&disp=inline&realattid=f_h85xz9341&safe=1&zw&saddbat=ANGjdJ80ygnCoNeDC9IW_BeMrbaHsPMOyCZVHVhJiRIb3Bx7zYzvotbkSu4Ccl7L0bQ1g5MU-3qVVpz8J1r1gDKNr8XmsVwUn2OFyRsKXsmPmN5ByoMPdpR6J6jnoAKCBl0NXk3N3z-z_JNVARkc-Hx5ES9qBL5FA-ZKaTmuzSeKpKxmNSdFFFnv2iKayFpJbUzhB5imQxy4e9jxrpdAxvt7ae91iAwECj5g2gu0ESl05x9Mrb2ydh-gwlMsZq8xyilMa4eAQnXVjdYsQSbQ3KhNgEozfBzXqxRo7TjHNK_ok2Ar0Qy9aPtoLFTdwh4T74kZ-yDJJiul1mPTobasPWv1oKBW76oaOybAqmnkbdbwkcgqMUgYs8r0iF3xsXfOavLfhJsnWi8ElxvrIMpgEsbMsyV9UXRGm3C-wG0QqNWfwVeLanr5ZcIIBlOs1LDY9xYQ-Vs0Xco1QfkCQydWI_vjGsbAAYOc2NtYkwHaayAx4bE12TuTOe1jX3mggpXKLhMoDixeomO4AUrTcX9XFP2UAUl96bBX-sbQmpZQ7Aqy9Q2f4MZZUc4LTksPgL9i5JtNsi6xSRH-GK9BC8RDjz8iih51LSkYfEj2aS_W9sOOdNiztWw7E3i0hyRld-f2bHxxIULepnkirx1aa0rzANR15JE7rLh5aopgb30-rQ
 * http://algs4.cs.princeton.edu/code/
 * https://mail-attachment.googleusercontent.com/attachment/u/0/?ui=2&ik=26879a90be&view=att&th=144181d1dbd12052&attid=0.1&disp=inline&realattid=f_hrgp4bvu0&safe=1&zw&saddbat=ANGjdJ_JIBSxUhjXUIqeJ0jzNlA-BHjIGbRoerYHZzzbM2NZW4qF3Gpz2UWsx-X0yr39Od8wJPHyPZ2VaL3zEdwzALacEgmHfOYsWz1cQqq2efevk65cT4oxD2xHMb9oyHkHy0IrCoBH_R18XRC08fj6Q-4_D0Fej7Scy1FwLL8l3UJYdTWMtG4cFghJIsFJGxF-FiZurJ7Yc_3ZEj5dA1-KxoJe2pEufyZoDR93C6-VjA-Iw6D7iJfLsh8r4MO1v-dlwQbut9TWVM-ccv5hjxkaT1N96mM90DUiUQV4849iKRbG2x_4mxuLAEGqMizeh7eaTrfZtL8MYqTI-dDQ53i9tbfCesyTExnZyUmDlD_Vxnkq8Sl5NXwxf-VBznUoC-248OsGl43SesmR8Ck5H49iLEG6_h4wrP9jZvPNOK2qYzmmwIVjfimdnPrNOY331mLhXPGvQ_UsTUJ5AC4L2z5IdVGnrwa-moX91ib6znpu7TNmf4yheAZeZiAixXKxmQYNKXlsuiDlYJFOlRGrgglk-y6hOqLIfXwYxGrY8zi7lxTS3Ggr-1nOUox14WwoCftRhiJ86XtwG1vNFC7ll6eO9qgIrDbkJQ7-tf1BTHsgxwf1NVXLw3-qUjJm4QA1RQerQkJnGhXw3mELyCvNrZ3V2QabCsHvEEuBlVAE8w
 * https://www.khanacademy.org/computing/computer-science/algorithms
 * http://fr.wikihow.com/gagner-une-partie-de-Risk
 */
public class App {

    /**
     * @return the full Edge
     * @throws ElementNotFoundException if asked Edge is not found
     */
    public static Edge getEdge(List<Edge> edges, Edge partialEdgeToFind) {
        return edges.stream()
                .filter(edge -> edge.equals(partialEdgeToFind))
                .findAny().orElseThrow(ElementNotFoundException::new);
    }

    /**
     * @return the asked Factory or null if not found
     */
    public static Factory getFactory(List<Factory> factories, int id) {
        return factories.stream()
                .filter(factory -> factory.getId() == id)
                .findAny().orElse(null);
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int factoryCount = in.nextInt(); // the number of factories
        List<Factory> factories = new ArrayList<>(factoryCount);
        System.err.println("factoryCount : " + factoryCount);

        int linkCount = in.nextInt(); // the number of links between factories
        List<Edge> edges = new ArrayList<>(linkCount);
        System.err.println("linkCount : " + linkCount);
        for (int i = 0; i < linkCount; i++) {
            int factory1 = in.nextInt();
            int factory2 = in.nextInt();
            int distance = in.nextInt();

            Edge edge = new Edge(factory1, factory2, distance);
            System.err.println("edge : " + edge.toString());
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
                        Factory factory = getFactory(factories, entityId);
                        if (factory != null) {
                            int previousProduction = factory.getProduction();
                            if (previousProduction != production) {
                                System.err.println("production changed !");
                            }
                            factory.setOwner(owner);
                            factory.setStockOfCyborgs(nbOfCyborgsInFactory);
                            factory.setProduction(production);
                            System.err.println("factory updated : " + factory.toString());
                        } else {
                            factory = new Factory(entityId, owner, nbOfCyborgsInFactory, production);
                            System.err.println("factory created : " + factory.toString());
                            factories.add(factory);
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
                        System.err.println("troop created : " + troop.toString());
                        edge.addTroop(troop);
                        break;
                    default:
                        break;
                }

            }

            calculateOpportunityScore(factories, edges);
            calculateDangerScore(factories, edges);

            takeADecision(factories, edges);
        }
    }

    /**
     * based on cyborgs number, owner, troops on the way, distance,...
     */
    public static void calculateOpportunityScore(List<Factory> factories, List<Edge> edges) {
        List<Factory> notOwnedFactories = factories.stream()
                .filter(factory1 -> factory1.getOwner() != Owner.ally).collect(Collectors.toList());

        for (Factory factory : notOwnedFactories) {
            List<Edge> connectedEdge = getConnectedEdges(factory, edges);
            int score = factory.getStockOfCyborgs();
            int nbOfHostileFactories = 0; // todo ?
            for (Edge edge : connectedEdge) {
                List<Troop> hostileTroops = edge.getTroops().stream()
                        .filter(troop -> troop.getOwner() != Owner.ally).collect(Collectors.toList());
                for (Troop troop : hostileTroops) {
                    score += troop.getNbOfCyborgs(); // todo : use remainingDistance ?
                }

                List<Troop> allyTroops = edge.getTroops().stream()
                        .filter(troop -> troop.getOwner() == Owner.ally).collect(Collectors.toList());
                for (Troop troop : allyTroops) {
                    score -= troop.getNbOfCyborgs(); // todo : use remainingDistance ?
                }
            }
            score += nbOfHostileFactories; // todo : ajouter ou multiplier ? ou ne rien en faire ?

            // vraiment ? parce que même si la production = 0 on peut utiliser la factory comme passerelle
            // todo : plutot utiliser l'indicateur Production comme critère de tri/priorité mais ailleurs dans le code
            score = factory.getProduction() == 0 ? 100 : score / factory.getProduction();

            factory.setOpportunityScore(score);
        }
    }

    /**
     * what if I am on danger ? <br/>
     * based on incoming hostile troops vs incoming ally troops
     */
    public static void calculateDangerScore(List<Factory> factories, List<Edge> edges) {
        List<Factory> ownedFactories = factories.stream()
                .filter(factory -> factory.getOwner() == Owner.ally).collect(Collectors.toList());

        for (Factory factory : ownedFactories) {
            int score = -factory.getStockOfCyborgs();
            List<Edge> connectedEdge = getConnectedEdges(factory, edges);
            for (Edge edge : connectedEdge) {
                List<Troop> hostileTroops = edge.getTroops().stream()
                        .filter(troop -> troop.getOwner() != Owner.ally).collect(Collectors.toList());
                for (Troop troop : hostileTroops) {
                    score += troop.getNbOfCyborgs(); // todo : use remainingDistance ?
                }

                List<Troop> allyTroops = edge.getTroops().stream()
                        .filter(troop -> troop.getOwner() == Owner.ally).collect(Collectors.toList());
                for (Troop troop : allyTroops) {
                    score -= troop.getNbOfCyborgs(); // todo : use remainingDistance ?
                }
            }
            factory.setDangerScore(score);
        }
    }

    /**
     * Any valid action, such as "WAIT" or "MOVE source destination cyborgs" <br/>
     * based on production & distance
     */
    public static String takeADecision(List<Factory> factories, List<Edge> edges) {
        String action = "WAIT";

        // todo : trier les factories pour avoir les > production en premier ?

        // todo filtrer par à nous/pas à nous
        for (Factory factory : factories) {
            if (factory.getOwner() == Owner.ally && factory.getProduction() == 0) {
                // en priorité ? déplacer tous les cyborgs alliés - 1 ailleurs
                action = move(factory.getId(), getBestNeighborId(factory, factories, edges), factory.getStockOfCyborgs() - 1);
                break;
            } else {
                // todo
                switch (factory.getProduction()) {
                    case 3:
                        // si alliée && en danger : protéger
                        // si non alliée && opportunité : conquérir
                        break;
                    case 2:
                        break;
                    case 1:
                        break;
                    case 0:
                        // déplacer tous les cyborgs alliés - 1 ailleurs
                        break;
                    default:
                        break;
                }
            }
        }
        System.out.println(action);
        return action;
    }

    private static int getBestNeighborId(Factory factory, List<Factory> factories, List<Edge> edges) {
        List<Edge> connectedEdge = getConnectedEdges(factory, edges);

        // todo : to test, je veux que les plus grosses productions soient au début de la List
        List<Factory> sortedFactories = factories.stream()
                .sorted((o1, o2) -> o2.getProduction() - o1.getProduction())
                .collect(Collectors.toList());

        // todo : récupérer la première factory présente dans sortedFactories qui soit aussi dans connectedEdge
        int bestNeighborId = 0;
        return bestNeighborId;
    }

    private static List<Edge> getConnectedEdges(Factory factory, List<Edge> edges) {
        return edges.stream()
                .filter(edge -> edge.getFactoryId_A() == factory.getId() || edge.getFactoryId_B() == factory.getId())
                .collect(Collectors.toList());
    }

    public static String move(int source, int destination, int cyborgs) {
        return "MOVE " + source + " " + destination + " " + cyborgs;
    }
}
