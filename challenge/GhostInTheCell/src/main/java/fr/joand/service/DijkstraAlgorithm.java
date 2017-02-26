package fr.joand.service;


import fr.joand.model.Edge;
import fr.joand.model.Graph;
import fr.joand.model.Factory;

import java.util.*;

public class DijkstraAlgorithm {

    private final List<Factory> nodes;
    private final List<Edge> edges;
    private Set<Factory> settledNodes;
    private Set<Factory> unSettledNodes;
    private Map<Factory, Factory> predecessors;
    private Map<Factory, Integer> distance;

    public DijkstraAlgorithm(Graph graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<Factory>(graph.getFactories());
        this.edges = new ArrayList<Edge>(graph.getEdges());
    }

    public void execute(Factory source) {
        settledNodes = new HashSet<Factory>();
        unSettledNodes = new HashSet<Factory>();
        distance = new HashMap<Factory, Integer>();
        predecessors = new HashMap<Factory, Factory>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Factory node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Factory node) {
        List<Factory> adjacentNodes = getNeighbors(node);
        for (Factory target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(Factory node, Factory target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge.getDistance();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Factory> getNeighbors(Factory node) {
        List<Factory> neighbors = new ArrayList<Factory>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private Factory getMinimum(Set<Factory> factories) {
        Factory minimum = null;
        for (Factory factory : factories) {
            if (minimum == null) {
                minimum = factory;
            } else {
                if (getShortestDistance(factory) < getShortestDistance(minimum)) {
                    minimum = factory;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(Factory factory) {
        return settledNodes.contains(factory);
    }

    private int getShortestDistance(Factory destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /**
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<Factory> getPath(Factory target) {
        LinkedList<Factory> path = new LinkedList<Factory>();
        Factory step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

}