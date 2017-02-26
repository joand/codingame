package fr.joand.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Edges are given with an orientation (from-to) BUT troops can move both ways
 */
public class Edge {

    private final int factory_A;
    private final int factory_B;
    private final int distance;

    private List<Troop> troops = new ArrayList<>();

    public Edge(int factory_A, int factory_B, int distance) {
        this.factory_A = factory_A;
        this.factory_B = factory_B;
        this.distance = distance;
    }

    public Edge(int factory_A, int factory_B) {
        this.factory_A = factory_A;
        this.factory_B = factory_B;
        this.distance = -1;
    }

    public void addTroop(Troop troop) {
        troops.add(troop);
    }

    /**
     * @return a copy of troops
     */
    public List<Troop> getTroops() {
        return new ArrayList<>(troops);
    }

    public int getFactory_A() {
        return factory_A;
    }

    public int getFactory_B() {
        return factory_B;
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
        return (factory_A == edge.factory_A &&
                factory_B == edge.factory_B) ||
                (factory_A == edge.factory_B &&
                        factory_B == edge.factory_A);
    }

    @Override
    public int hashCode() {
        return Objects.hash(factory_A, factory_B);
    }
}