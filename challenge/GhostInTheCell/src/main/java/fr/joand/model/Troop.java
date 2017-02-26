package fr.joand.model;


public class Troop {
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
}
