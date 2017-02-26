package fr.joand.model;

/**
 * Created by user on 25/02/2017.
 */
public class Troop {
    final private int id;
    private Owner owner;
    private Edge edge;
    private int nbOfCyborgs;
    private int remainingDistance;

    public Troop(int id, Owner owner, Edge edge, int nbOfCyborgs, int remainingDistance) {
        this.id = id;
        this.owner = owner;
        this.edge = edge;
        this.nbOfCyborgs = nbOfCyborgs;
        this.remainingDistance = remainingDistance;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public Edge getEdge() {
        return edge;
    }

    public void setEdge(Edge edge) {
        this.edge = edge;
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

    public void setNbOfCyborgs(int nbOfCyborgs) {
        this.nbOfCyborgs = nbOfCyborgs;
    }
}
