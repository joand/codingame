package fr.joand.model;


public class Edge  {
    /**
     * useless id
     * */
    private String id = "-1";
    private final Factory source;
    private final Factory destination;
    private final int distance;

    public Edge(Factory source, Factory destination, int distance) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
    }

    public Edge(String id, Factory source, Factory destination, int distance) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.distance = distance;
    }

    public String getId() {
        return id;
    }
    public Factory getDestination() {
        return destination;
    }

    public Factory getSource() {
        return source;
    }
    public int getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }


}