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
     * @return the full Edge
     * @throws ElementNotFoundException if asked Edge is not found
     */
    public static Edge getEdge(List<Edge> edges, int factoryId_A, int factoryId_B) {
        return edges.stream()
                .filter(edge -> (edge.getFactoryId_A() == factoryId_A && edge.getFactoryId_B() == factoryId_B)
                        ||
                        (edge.getFactoryId_A() == factoryId_B && edge.getFactoryId_B() == factoryId_A)
                )
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
        for (int id = 0; id < factoryCount; id++) {
            Factory factory = new Factory(id);
            factories.add(factory);
        }
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
                            System.err.println("!!! FACTORY NOT FOUND !!!");
                        }
                        break;
                    case "TROOP":
                        int factoryIdSource = arg2;
                        int factoryIdDestination = arg3;
                        Edge edge = getEdge(edges, factoryIdSource, factoryIdDestination);

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
            clearTroopsFrom(edges);
        }
    }

    private static void clearTroopsFrom(List<Edge> edges) {
        edges.stream().forEach(edge -> edge.clearTroops());
    }

    /**
     * based on production [divide by] distance <br/>
     * the bigger the better !
     */
    public static void calculateOpportunityScore(List<Factory> factories, List<Edge> edges) {
        List<Factory> notOwnedFactories = factories.stream()
                .filter(factory1 -> factory1.getOwner() != Owner.ally).collect(Collectors.toList());

        for (Factory factory : notOwnedFactories) {
            int maxScore = 0;
            for (Factory neighbor : getAllyNeighbors(factories, edges, factory)) {
                float score = (float) factory.getProduction() / (float) (getEdge(edges, factory.getId(), neighbor.getId()).getDistance() + factory.getStockOfCyborgs());
                int intScore = Math.round(score * 10000);
                maxScore = Math.max(maxScore, intScore);
            }

            factory.setOpportunityScore(maxScore);
        }
    }

    public static List<Factory> getAllyNeighbors(List<Factory> factories, List<Edge> edges, Factory factory) {
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
        return allNeighbors.stream().filter(factory1 -> factory1.getOwner() == Owner.ally).collect(Collectors.toList());
    }

    /**
     * calculate the number of cyborgs needed to defend (ally) or conquer (enemy) a factory <br/>
     * based on incoming hostile troops vs incoming ally troops
     */
    public static void calculateDangerScore(List<Factory> factories, List<Edge> edges) {
        for (Factory factory : factories) {
            int score = factory.getOwner() == Owner.ally ? factory.getStockOfCyborgs() * -1 : factory.getStockOfCyborgs();
            List<Edge> connectedEdge = getConnectedEdges(factory, edges);
            for (Edge edge : connectedEdge) {
                List<Troop> hostileTroops = edge.getTroops().stream()
                        .filter(troop -> troop.getOwner() != Owner.ally).collect(Collectors.toList());
                for (Troop troop : hostileTroops) {
                    score += troop.getNbOfCyborgs();
                }

                List<Troop> allyTroops = edge.getTroops().stream()
                        .filter(troop -> troop.getOwner() == Owner.ally).collect(Collectors.toList());
                for (Troop troop : allyTroops) {
                    score -= troop.getNbOfCyborgs();
                }
            }
            factory.setDangerScore(score);
        }
    }

    /**
     * Any valid action, such as "WAIT" or "MOVE source destination cyborgs" <br/>
     * based on production & distance
     */
    public static void takeADecision(List<Factory> factories, List<Edge> edges) {
        // trier les factories pour avoir les > opportunity score en premier
        List<Factory> opportunitySorted = factories.stream().sorted((o1, o2) -> o2.getOpportunityScore() - o1.getOpportunityScore()).collect(Collectors.toList());
        List<Factory> dangerSorted = factories.stream().sorted((o1, o2) -> o2.getDangerScore() - o1.getDangerScore()).collect(Collectors.toList());

        // filtrer par Owner
        List<Factory> toDefend = dangerSorted.stream().filter(factory -> factory.getOwner() == Owner.ally).collect(Collectors.toList());
        List<Factory> toConquer = opportunitySorted.stream().filter(factory -> factory.getOwner() != Owner.ally).collect(Collectors.toList());

        //if (opening(factories, allies)) {
        // conquérir le plus rapidement

        StringBuffer action = new StringBuffer();
        // offensive
        int weaponSize = 2;
        for (Factory target : toConquer) {
            // je prend celui qui a le plus de cyborgs... c'est plus facile à trouver
            Factory source = getAllyNeighbors(factories, edges, target).stream()
                    .sorted((o1, o2) -> o2.getStockOfCyborgs() - o1.getStockOfCyborgs())
                    .findFirst().get();
                action.append(move(source.getId(), target.getId(), Math.round((float) source.getStockOfCyborgs() / (float) weaponSize)));
            }

        // defensive
        int shieldSize = 2;
        for (Factory target : toDefend) {
            Factory source = getAllyNeighbors(factories, edges, target).stream()
                    .sorted((o1, o2) -> o2.getStockOfCyborgs() - o1.getStockOfCyborgs())
                    .findFirst().orElse(null);
            if (target.getDangerScore() > 0 && source != null) {
                action.append(move(source.getId(), target.getId(), Math.round((float) source.getStockOfCyborgs() / (float) shieldSize)));
            }
        }
        /*
        } else if (midGame()) {
            // construire, défendre et attaquer
        } else if (endGame()) {
            // achever
        }
        //*/
        System.out.println(action.toString() + "MSG end turn");
    }

    private static boolean opening(List<Factory> factories, List<Factory> allies) {
        return allies.size() / factories.size() < 0.5;
    }

    private static boolean midGame() {
        return false;
    }

    private static boolean endGame() {
        return false;
    }

    private static List<Edge> getConnectedEdges(Factory factory, List<Edge> edges) {
        return edges.stream()
                .filter(edge -> edge.getFactoryId_A() == factory.getId() || edge.getFactoryId_B() == factory.getId())
                .collect(Collectors.toList());
    }

    public static String move(int source, int destination, int cyborgs) {
        return "MOVE " + source + " " + destination + " " + cyborgs + ";";
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
     * */
    public Edge(int factoryId_A, int factoryId_B) {
        this.factoryId_A = factoryId_A;
        this.factoryId_B = factoryId_B;
        this.distance = -1;
    }

    public void addTroop(Troop troop) {
        troops.add(troop);
    }

    public void clearTroops(){
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
     * */
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
    private int dangerScore = 0;

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

    public int getOpportunityScore() {
        return opportunityScore;
    }

    public void setOpportunityScore(int opportunityScore) {
        this.opportunityScore = opportunityScore;
    }

    public int getDangerScore() {
        return dangerScore;
    }

    public void setDangerScore(int dangerScore) {
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

class ElementNotFoundException extends RuntimeException {
}