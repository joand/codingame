package fr.joand.model;


import java.util.List;

public class Graph {
    private final List<Factory> factories;
    private final List<Edge> edges;

    public Graph(List<Factory> factories, List<Edge> edges) {
        this.factories = factories;
        this.edges = edges;
    }

    public List<Factory> getFactories() {
        return factories;
    }

    public List<Edge> getEdges() {
        return edges;
    }



}