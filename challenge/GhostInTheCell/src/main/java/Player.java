import java.util.*;
import java.util.stream.Collectors;
import java.util.Objects;

/**
 * sources :
 * http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html
 * https://mail-attachment.googleusercontent.com/attachment/u/0/?ui=2&ik=26879a90be&view=att&th=13a50271fc085591&attid=0.2&disp=inline&realattid=f_h85xz9341&safe=1&zw&saddbat=ANGjdJ80ygnCoNeDC9IW_BeMrbaHsPMOyCZVHVhJiRIb3Bx7zYzvotbkSu4Ccl7L0bQ1g5MU-3qVVpz8J1r1gDKNr8XmsVwUn2OFyRsKXsmPmN5ByoMPdpR6J6jnoAKCBl0NXk3N3z-z_JNVARkc-Hx5ES9qBL5FA-ZKaTmuzSeKpKxmNSdFFFnv2iKayFpJbUzhB5imQxy4e9jxrpdAxvt7ae91iAwECj5g2gu0ESl05x9Mrb2ydh-gwlMsZq8xyilMa4eAQnXVjdYsQSbQ3KhNgEozfBzXqxRo7TjHNK_ok2Ar0Qy9aPtoLFTdwh4T74kZ-yDJJiul1mPTobasPWv1oKBW76oaOybAqmnkbdbwkcgqMUgYs8r0iF3xsXfOavLfhJsnWi8ElxvrIMpgEsbMsyV9UXRGm3C-wG0QqNWfwVeLanr5ZcIIBlOs1LDY9xYQ-Vs0Xco1QfkCQydWI_vjGsbAAYOc2NtYkwHaayAx4bE12TuTOe1jX3mggpXKLhMoDixeomO4AUrTcX9XFP2UAUl96bBX-sbQmpZQ7Aqy9Q2f4MZZUc4LTksPgL9i5JtNsi6xSRH-GK9BC8RDjz8iih51LSkYfEj2aS_W9sOOdNiztWw7E3i0hyRld-f2bHxxIULepnkirx1aa0rzANR15JE7rLh5aopgb30-rQ
 * http://algs4.cs.princeton.edu/code/
 * https://mail-attachment.googleusercontent.com/attachment/u/0/?ui=2&ik=26879a90be&view=att&th=144181d1dbd12052&attid=0.1&disp=inline&realattid=f_hrgp4bvu0&safe=1&zw&saddbat=ANGjdJ_JIBSxUhjXUIqeJ0jzNlA-BHjIGbRoerYHZzzbM2NZW4qF3Gpz2UWsx-X0yr39Od8wJPHyPZ2VaL3zEdwzALacEgmHfOYsWz1cQqq2efevk65cT4oxD2xHMb9oyHkHy0IrCoBH_R18XRC08fj6Q-4_D0Fej7Scy1FwLL8l3UJYdTWMtG4cFghJIsFJGxF-FiZurJ7Yc_3ZEj5dA1-KxoJe2pEufyZoDR93C6-VjA-Iw6D7iJfLsh8r4MO1v-dlwQbut9TWVM-ccv5hjxkaT1N96mM90DUiUQV4849iKRbG2x_4mxuLAEGqMizeh7eaTrfZtL8MYqTI-dDQ53i9tbfCesyTExnZyUmDlD_Vxnkq8Sl5NXwxf-VBznUoC-248OsGl43SesmR8Ck5H49iLEG6_h4wrP9jZvPNOK2qYzmmwIVjfimdnPrNOY331mLhXPGvQ_UsTUJ5AC4L2z5IdVGnrwa-moX91ib6znpu7TNmf4yheAZeZiAixXKxmQYNKXlsuiDlYJFOlRGrgglk-y6hOqLIfXwYxGrY8zi7lxTS3Ggr-1nOUox14WwoCftRhiJ86XtwG1vNFC7ll6eO9qgIrDbkJQ7-tf1BTHsgxwf1NVXLw3-qUjJm4QA1RQerQkJnGhXw3mELyCvNrZ3V2QabCsHvEEuBlVAE8w
 * https://www.khanacademy.org/computing/computer-science/algorithms
 * http://fr.wikihow.com/gagner-une-partie-de-Risk
 * StarCraft
 */
class Player {

    /**
     * @return the full Edge or null if not found
     */
    public static Edge getEdge(List<Edge> edges, int factoryId_A, int factoryId_B) {
        return edges.stream()
                .filter(edge -> (edge.getFactoryId_A() == factoryId_A && edge.getFactoryId_B() == factoryId_B)
                        ||
                        (edge.getFactoryId_A() == factoryId_B && edge.getFactoryId_B() == factoryId_A)
                )
                .findFirst().orElse(null);
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
        for (int id = 0; id < factoryCount; id++) {
            Factory factory = new Factory(id);
            factories.add(factory);
        }

        int linkCount = in.nextInt(); // the number of links between factories
        List<Edge> edges = new ArrayList<>(linkCount);
        for (int i = 0; i < linkCount; i++) {
            int factory1 = in.nextInt();
            int factory2 = in.nextInt();
            int distance = in.nextInt();

            Edge edge = new Edge(factory1, factory2, distance);
            edges.add(edge);
        }

        List<Bomb> bombs = new ArrayList<>(4);
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
                            factory.setOwner(owner);
                            factory.setStockOfCyborgs(nbOfCyborgsInFactory);
                            factory.setProduction(production);
                        }
                        break;
                    case "TROOP":
                        int factoryIdSource = arg2;
                        int factoryIdDestination = arg3;
                        Edge edge = getEdge(edges, factoryIdSource, factoryIdDestination);

                        int nbOfCyborgsInTroop = arg4;
                        int remainingDistance = arg5;

                        Troop troop = new Troop(entityId, owner, nbOfCyborgsInTroop, remainingDistance, factoryIdDestination);
                        if (edge != null) {
                            edge.addTroop(troop);
                        } else {
                            System.err.println("IMPOSSIBLE ERROR : NO EDGE FOUND (no hedge fund...)");
                        }
                        break;
                    case "BOMB":
                        int sourceFactoryId = arg2;
                        int targetFactoryId = arg3;
                        int countdown = arg4;
                        Bomb bomb = new Bomb(entityId, owner, sourceFactoryId, targetFactoryId, countdown);
                        bombs.add(bomb);
                        break;
                    default:
                        break;
                }

            }

            boolean finishToComputeDangerScore = computeDangerScore(factories, edges);
            computeOpportunityScore(factories, edges, finishToComputeDangerScore);

            takeADecision(factories, edges, bombs);
            clearTroopsFrom(edges);
        }
    }

    private static void clearTroopsFrom(List<Edge> edges) {
        edges.stream().forEach(edge -> edge.clearTroops());
    }

    /**
     * MUST BE CALLED AFTER computeDangerScore <br/>
     * based on : <br/>
     * FOR ENEMY   production [divide by] distance from ally + danger score <br/>
     * FOR NEUTRAL production + distance from enemy [divide by] danger score <br/>
     * the bigger the better !
     *
     */
    public static void computeOpportunityScore(List<Factory> factories, List<Edge> edges, boolean isDangerScoreComputed) {
        if (isDangerScoreComputed) {
            List<Factory> enemiesFactories = factories.stream()
                    .filter(factory1 -> factory1.getOwner() == Owner.enemy).collect(Collectors.toList());

            for (Factory enemyFactory : enemiesFactories) {
                int maxScore = 0;
                for (Factory allyFactory : getAllyNeighbors(factories, edges, enemyFactory)) {
                    Edge edge = getEdge(edges, enemyFactory.getId(), allyFactory.getId());
                    if (edge != null) {
                        float score = (float) enemyFactory.getProduction() / (edge.getDistance() + enemyFactory.getDangerScore());
                        int intScore = Math.round(score * 10000);
                        maxScore = Math.max(maxScore, intScore);
                    }
                }

                enemyFactory.setOpportunityScore(maxScore);
            }

            List<Factory> neutralFactories = factories.stream()
                    .filter(factory1 -> factory1.getOwner() == Owner.neutral).collect(Collectors.toList());

            for (Factory neutralFactory : neutralFactories) {
                int maxScore = 0;
                for (Factory enemyFactory : getEnemyNeighbors(factories, edges, neutralFactory)) {
                    Edge edge = getEdge(edges, neutralFactory.getId(), enemyFactory.getId());
                    if (edge != null) {
                        float score = ((float) (neutralFactory.getProduction() + edge.getDistance())) / neutralFactory.getDangerScore();
                        int intScore = Math.round(score * 10000);
                        maxScore = Math.max(maxScore, intScore);
                    }
                }

                neutralFactory.setOpportunityScore(maxScore);
            }
        } else {
            System.err.println("IMPOSSIBLE ERROR : DANGER SCORE HAS NOT BEEN COMPUTED");
        }
    }

    public static Factory getNearestAllyNeighbors(List<Factory> factories, List<Edge> edges, Factory factory) {
        List<Factory> allyNeighbors = getAllyNeighbors(factories, edges, factory);

        if (allyNeighbors.size() == 1) {
            return allyNeighbors.get(0);
        }

        int minDistance = 100000;
        Factory result = null;
        for (Factory allyNeighbor : allyNeighbors) {
            Edge edge = getEdge(edges, allyNeighbor.getId(), factory.getId());
            if (edge != null) {
                int distance = edge.getDistance();
                if (distance < minDistance) {
                    minDistance = distance;
                    result = allyNeighbor;
                }
            }
        }
        return result;
    }

    public static List<Factory> getAllyNeighbors(List<Factory> factories, List<Edge> edges, Factory factory) {
        List<Factory> allNeighbors = getNeighbors(factories, edges, factory);
        return allNeighbors.stream().filter(factory1 -> factory1.getOwner() == Owner.ally).collect(Collectors.toList());
    }

    public static List<Factory> getEnemyNeighbors(List<Factory> factories, List<Edge> edges, Factory factory) {
        List<Factory> allNeighbors = getNeighbors(factories, edges, factory);
        return allNeighbors.stream().filter(factory1 -> factory1.getOwner() == Owner.enemy).collect(Collectors.toList());
    }

    private static List<Factory> getNeighbors(List<Factory> factories, List<Edge> edges, Factory factory) {
        /*
         * garder les edges qui ont la factory sur une extrémité
         * puis récupérer les factories de ces edges != factory de référence
         * */
        List<Edge> edgeNeighbors = getConnectedEdges(factory, edges);
        List<Factory> allNeighbors = new ArrayList<>();
        for (Edge edge : edgeNeighbors) {
            allNeighbors.add(
                    getFactory(
                            factories,
                            edge.getFactoryId_A() != factory.getId() ?
                                    edge.getFactoryId_A() : edge.getFactoryId_B())
            );
        }
        return allNeighbors;
    }

    /**
     * calculate the number of cyborgs needed to defend (ally) or to conquer (enemy) a factory <br/>
     * based on : <br/>
     * incoming hostile troops <br/>
     * incoming ally troops <br/>
     * troop Remaining Distance
     */
    public static boolean computeDangerScore(List<Factory> factories, List<Edge> edges) {
        for (Factory factory : factories) {
            computeDangerScore(factory, edges);
        }
        return true;
    }

    /**
     * calculate the number of cyborgs needed to defend (ally) or to conquer (enemy) a factory <br/>
     * based on : <br/>
     * incoming hostile troops <br/>
     * incoming ally troops <br/>
     * troop Remaining Distance
     */
    private static void computeDangerScore(Factory factory, List<Edge> edges) {
        float score = factory.getOwner() == Owner.ally ?
                factory.getStockOfCyborgs() * -1 : factory.getStockOfCyborgs();
        List<Edge> connectedEdge = getConnectedEdges(factory, edges);
        for (Edge edge : connectedEdge) {
            List<Troop> hostileTroops = edge.getTroops().stream()
                    .filter(troop -> troop.getOwner() != Owner.ally
                            && troop.getDestinationFactoryId() == factory.getId()
                            && troop.getRemainingDistance() != 0
                    )
                    .collect(Collectors.toList());
            for (Troop troop : hostileTroops) {
                score += troop.getNbOfCyborgs() / troop.getRemainingDistance();
            }

            List<Troop> allyTroops = edge.getTroops().stream()
                    .filter(troop -> troop.getOwner() == Owner.ally
                            && troop.getDestinationFactoryId() == factory.getId()
                            && troop.getRemainingDistance() != 0)
                    .collect(Collectors.toList());
            for (Troop troop : allyTroops) {
                score -= troop.getNbOfCyborgs() / troop.getRemainingDistance();
            }
        }
        factory.setDangerScore(score == 0 ? 1 : score);
    }

    /**
     * Any valid action, such as "WAIT" or "MOVE source destination cyborgs" <br/>
     * based on production & distance
     */
    public static void takeADecision(List<Factory> factories, List<Edge> edges, List<Bomb> bombs) {
        List<Factory> allies = factories.stream()
                .filter(factory -> factory.getOwner() == Owner.ally)
                .sorted((o1, o2) -> Math.round(o2.getDangerScore()) - Math.round(o1.getDangerScore()))
                .collect(Collectors.toList());
        StringBuffer action = new StringBuffer();
        if (opening()) {
            // conquérir le plus rapidement : attaque + défense dans cet ordre
            sendAllBombs(factories, edges, bombs, action); // always first
            if (allies.size() == 1) {
                spread(factories, edges, action);
            } else {
                assault(factories, edges, action);
            }
            counterMeasure(factories, edges, allies, action, 0.5f);
            increaseProduction(factories, edges, action);
        } else if (midGame()) {
            // construire et défendre only
            sendAllBombs(factories, edges, bombs, action);
            counterMeasure(factories, edges, allies, action, 0.5f);
            increaseProduction(factories, edges, action);
        } else if (endGame()) {
            // achever
            assault(factories, edges, action);
        }

        System.out.println(action.toString() + "MSG soli Deo gloria");
    }

    private static void sendAllBombs(List<Factory> factories, List<Edge> edges, List<Bomb> bombs, StringBuffer action) {
        List<Bomb> usedBombs = bombs.stream().filter(bomb -> bomb.getOwner() == Owner.ally).collect(Collectors.toList());
        if (usedBombs.size() == 0) {
            List<Factory> targets = factories.stream()
                    .filter(factory -> factory.getOwner() == Owner.enemy && factory.getProduction() >= 2)
                    .sorted((o1, o2) -> o2.getProduction() - o1.getProduction())
                    .sorted((o1, o2) -> Math.round(o2.getDangerScore()) - Math.round(o1.getDangerScore()))
                    .collect(Collectors.toList());
            if (targets.size() >= 2) {
                sendBomb(factories, edges, targets.get(0), action);
                sendBomb(factories, edges, targets.get(1), action);
            }
        } else if (usedBombs.size() == 1) {
            List<Factory> targets = factories.stream()
                    .filter(factory -> factory.getOwner() == Owner.enemy && factory.getProduction() == 3)
                    .sorted((o1, o2) -> Math.round(o2.getDangerScore()) - Math.round(o1.getDangerScore()))
                    .collect(Collectors.toList());
            if (!targets.isEmpty()) {
                sendBomb(factories, edges, targets.get(0), action);
            }
        }
    }

    private static void sendBomb(List<Factory> factories, List<Edge> edges, Factory target, StringBuffer action) {
        int source = getNearestAllyNeighbors(factories, edges, target).getId();
        int destination = target.getId();
        action.append("BOMB " + source + " " + destination + ";");
        increaseProduction(factories, edges, action);
        // todo : ici lancer l'assaut 1 tour après.
        //assault(factories, edges, action, target);
    }

    private static void increaseProduction(List<Factory> factories, List<Edge> edges, StringBuffer action) {
        // int increaseSpeed = 1;
        // on peut faire dans cet ordre : 30,20,10
        List<Factory> increaseReady = factories.stream()
                .filter(factory -> factory.getOwner() == Owner.ally
                        && factory.getStockOfCyborgs() > factory.getDangerScore() + 10)
                .collect(Collectors.toList());
        for (Factory provider : increaseReady) {
            if (provider.getProduction() < 3) {
                action.append("INC " + provider.getId() + ";");
                provider.setStockOfCyborgs(provider.getStockOfCyborgs() - 10);
                provider.setProduction(provider.getProduction() + 1);
            } else {
                // envoyer les 10 cyborgs au voisin le plus proche qui a factory.getProduction() < 3
                Factory destination = getAllyNeighbors(factories, edges, provider).stream()
                        .filter(ally -> ally.getProduction() < 3)
                        .sorted((o1, o2) -> Math.round(o1.getDangerScore()) - Math.round(o2.getDangerScore()))
                        .findFirst().orElse(null);
                if (destination != null) {
                    move(provider.getId(), destination.getId(), 10, factories, edges);
                }
            }
        }
    }

    private static void counterMeasure(List<Factory> factories, List<Edge> edges, List<Factory> toDefend, StringBuffer action, float shieldSize) {
        // defensive
        for (Factory target : toDefend) {
            Factory strongestSource = getAllyNeighbors(factories, edges, target).stream()
                    .sorted((o1, o2) -> o2.getStockOfCyborgs() - o1.getStockOfCyborgs())
                    .findFirst().orElse(null);
/*
            int strongestDist = getEdge(edges, target.getId(), strongestSource.getId()).getDistance();
            Factory nearestSource = getNearestAllyNeighbors(factories, edges, target);
            int nearestDistance = getEdge(edges, target.getId(), nearestSource.getId()).getDistance();
//*/
            if (target.getDangerScore() > 0) {
                if (strongestSource != null) {
                    int strongest = Math.round((float) strongestSource.getStockOfCyborgs() * shieldSize);
                    action.append(move(strongestSource.getId(), target.getId(), strongest, factories, edges));
                }
                /*
                if (nearestSource != null) {
                    int nearest = Math.round((float) nearestSource.getStockOfCyborgs() * shieldSize);
                    action.append(move(nearestSource.getId(), target.getId(), nearest, factories));
                }
                //*/
            }
        }
    }

    private static void spread(List<Factory> factories, List<Edge> edges, StringBuffer action) {
        List<Factory> allies = factories.stream()
                .filter(factory -> factory.getOwner() == Owner.ally)
                .sorted((o1, o2) -> o2.getStockOfCyborgs() - o1.getStockOfCyborgs())
                .collect(Collectors.toList());

        List<Factory> toConquer = factories.stream()
                .filter(factory -> factory.getOwner() != Owner.ally)
                .sorted((o1, o2) -> o2.getOpportunityScore() - o1.getOpportunityScore())
                .collect(Collectors.toList());
        for (Factory ally : allies) {
            if (toConquer.size() >= 2) {
                int nbOfCyborgs = ally.getStockOfCyborgs() / 2;
                action.append(move(ally.getId(), toConquer.get(0).getId(), nbOfCyborgs, factories, edges));
                action.append(move(ally.getId(), toConquer.get(1).getId(), nbOfCyborgs, factories, edges));
            }
        }
    }

    /**
     * deadly only after spread
     */
    private static void assault(List<Factory> factories, List<Edge> edges, StringBuffer action) {
        List<Factory> toConquer = factories.stream()
                .filter(factory -> factory.getOwner() != Owner.ally /*&& factory.getProduction() > 0*/) // discutable
                .sorted((o1, o2) -> o2.getOpportunityScore() - o1.getOpportunityScore())
                .collect(Collectors.toList());

        // offensive
        /*
        pour une cible donnée, sélectionner les voisins alliés (proches) dont la somme des cyborgs > score danger de cible
        puis lancer l'assaut !
        */
        for (Factory target : toConquer) {
            assault(factories, edges, action, target);
        }
    }

    private static void assault(List<Factory> factories, List<Edge> edges, StringBuffer action, Factory target) {
        List<Factory> allyNeighbors = getAllyNeighbors(factories, edges, target);
        for (Factory source : allyNeighbors) {
            // on attaque juste ce qu'il faut
            if (target.getDangerScore() > 0) {
                int nbOfCyborgs = Math.round(target.getDangerScore()) + 1;
                action.append(move(source.getId(), target.getId(), nbOfCyborgs, factories, edges));
            }
        }
    }

    // todo
    public static boolean opening() {
        return true;
    }

    // todo
    public static boolean midGame() {
        return false;
    }

    // todo
    public static boolean endGame() {
        return false;
    }

    public static List<Edge> getConnectedEdges(Factory factory, List<Edge> edges) {
        return edges.stream()
                .filter(edge -> edge.getFactoryId_A() == factory.getId() || edge.getFactoryId_B() == factory.getId())
                .collect(Collectors.toList());
    }

    public static String move(int source, int destination, int nbOfCyborgs, List<Factory> factories, List<Edge> edges) {
        if (nbOfCyborgs < 0) {
            return "WAIT;";
        } else {
            // mettre à jour les stats
            Factory from = factories.stream().filter(factory -> factory.getId() == source).findFirst().orElse(null);
            if (from != null) {
                from.setStockOfCyborgs(from.getStockOfCyborgs() - nbOfCyborgs);
            }
            Factory to = factories.stream().filter(factory -> factory.getId() == destination).findFirst().orElse(null);
            if (to != null) {
                to.setStockOfCyborgs(to.getStockOfCyborgs() + nbOfCyborgs);
            }
            // maj les indicateurs
            Factory target = factories.stream().filter(factory -> factory.getId() == destination).findFirst().orElse(null);
            if (target != null) {
                computeDangerScore(target, edges);
                // computeOpportunityScore(); todo ?
            }
            // communiquer l'action
            return "MOVE " + source + " " + destination + " " + nbOfCyborgs + ";";
        }
    }
}

class Edge {

    private final int factoryId_A;
    private final int factoryId_B;
    private final int distance;

    private List<Troop> troops = new ArrayList<>();

    public Edge(int factoryId_A, int factoryId_B, int distance) {
        this.factoryId_A = factoryId_A;
        this.factoryId_B = factoryId_B;
        this.distance = distance;
    }

    /**
     * creates partial Edge with empty troops and invalid distance(-1)
     */
    public Edge(int factoryId_A, int factoryId_B) {
        this.factoryId_A = factoryId_A;
        this.factoryId_B = factoryId_B;
        this.distance = -1;
    }

    public void addTroop(Troop troop) {
        troops.add(troop);
    }

    public void clearTroops() {
        troops.clear();
    }

    /**
     * @return a copy of troops
     */
    public List<Troop> getTroops() {
        return new ArrayList<>(troops);
    }

    public int getFactoryId_A() {
        return factoryId_A;
    }

    public int getFactoryId_B() {
        return factoryId_B;
    }

    public int getDistance() {
        return distance;
    }

    /**
     * Edges are given with an orientation (from-to) BUT troops can move both ways
     * so this method tests only the facories' id in both ways
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return (factoryId_A == edge.factoryId_A &&
                factoryId_B == edge.factoryId_B) ||
                (factoryId_A == edge.factoryId_B &&
                        factoryId_B == edge.factoryId_A);
    }

    @Override
    public int hashCode() {
        return Objects.hash(factoryId_A, factoryId_B);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "factoryId_A=" + factoryId_A +
                ", factoryId_B=" + factoryId_B +
                ", distance=" + distance +
                '}';
    }
}

class Factory {
    final private int id;

    private Owner owner;
    private int stockOfCyborgs;
    /**
     * Each turn, every non-neutral factory produces between 0 and 3 cyborgs.
     * BUT once the factory is created, this number will not change !
     */
    private int production = 0;
    /**
     * used to calculate the opportunity score of this factory when this.owner != ally <br/>
     * a high score means good opportunity
     */
    private int opportunityScore = 0;

    /**
     * used to calculate the danger score of this factory <br/>
     * a high score means high danger. low score means safe. can be negative (low)
     */
    private float dangerScore = 0;

    public int getProduction() {
        return production;
    }

    public void setProduction(int production) {
        this.production = production;
    }

    public Factory(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getStockOfCyborgs() {
        return stockOfCyborgs;
    }

    public void setStockOfCyborgs(int stockOfCyborgs) {
        this.stockOfCyborgs = stockOfCyborgs;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    /**
     * MUST BE CALLED AFTER computeDangerScore <br/>
     * based on : <br/>
     * FOR ENEMY   production [divide by] distance from ally + danger score <br/>
     * FOR NEUTRAL production + distance from enemy [divide by] danger score <br/>
     * the bigger the better !
     */
    public int getOpportunityScore() {
        return opportunityScore;
    }

    /**
     * MUST BE CALLED AFTER computeDangerScore <br/>
     * based on : <br/>
     * FOR ENEMY   production [divide by] distance from ally + danger score <br/>
     * FOR NEUTRAL production + distance from enemy [divide by] danger score <br/>
     * the bigger the better !
     */
    public void setOpportunityScore(int opportunityScore) {
        this.opportunityScore = opportunityScore;
    }

    /**
     * calculate the number of cyborgs needed to defend (ally) or to conquer (enemy) a factory <br/>
     * based on : <br/>
     * incoming hostile troops <br/>
     * incoming ally troops <br/>
     * troop Remaining Distance
     */
    public float getDangerScore() {
        return dangerScore;
    }

    /**
     * calculate the number of cyborgs needed to defend (ally) or to conquer (enemy) a factory <br/>
     * based on : <br/>
     * incoming hostile troops <br/>
     * incoming ally troops <br/>
     * troop Remaining Distance
     */
    public void setDangerScore(float dangerScore) {
        this.dangerScore = dangerScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factory factory = (Factory) o;
        return id == factory.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Factory{" +
                "id=" + id +
                ", owner=" + owner +
                ", stockOfCyborgs=" + stockOfCyborgs +
                ", production=" + production +
                '}';
    }
}

class Troop {
    final private int id;
    final private Owner owner;
    final private int nbOfCyborgs;
    final private int destinationFactoryId;

    private int remainingDistance;

    public Troop(int id, Owner owner, int nbOfCyborgs, int remainingDistance, int destinationFactoryId) {
        this.id = id;
        this.owner = owner;
        this.nbOfCyborgs = nbOfCyborgs;
        this.remainingDistance = remainingDistance;
        this.destinationFactoryId = destinationFactoryId;
    }

    public int getDestinationFactoryId() {
        return destinationFactoryId;
    }

    public Owner getOwner() {
        return owner;
    }

    public int getId() {
        return id;
    }

    public int getRemainingDistance() {
        return remainingDistance;
    }

    public void setRemainingDistance(int remainingDistance) {
        this.remainingDistance = remainingDistance;
    }

    public int getNbOfCyborgs() {
        return nbOfCyborgs;
    }

    @Override
    public String toString() {
        return "Troop{" +
                "id=" + id +
                ", owner=" + owner +
                ", nbOfCyborgs=" + nbOfCyborgs +
                ", destinationFactoryId=" + destinationFactoryId +
                ", remainingDistance=" + remainingDistance +
                '}';
    }
}

enum Owner {
    ally(1), enemy(-1), neutral(0);

    final int id;

    Owner(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Owner get(int id) {
        for (Owner owner : values()) {
            if (owner.getId() == id) {
                return owner;
            }
        }
        return null;
    }
}

class Bomb {
    private int id;
    private Owner owner;
    private int sourceFactoryId;
    private int targetFactoryId;
    private int countdown;

    public Bomb(int id, Owner owner, int sourceFactoryId, int targetFactoryId, int countdown) {
        this.id = id;
        this.owner = owner;
        this.sourceFactoryId = sourceFactoryId;
        this.targetFactoryId = targetFactoryId;
        this.countdown = countdown;
    }

    public int getId() {
        return id;
    }

    public Owner getOwner() {
        return owner;
    }

    public int getSourceFactoryId() {
        return sourceFactoryId;
    }

    public int getTargetFactoryId() {
        return targetFactoryId;
    }

    public int getCountdown() {
        return countdown;
    }
}