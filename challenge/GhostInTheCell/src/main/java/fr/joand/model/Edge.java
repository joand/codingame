package fr.joand.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Edges are given with an orientation (from-to) BUT troops can move both ways
 */
public class Edge {

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