package fr.joand.model;

import java.util.Objects;

public class Factory {
    final private int id;

    private Owner owner;
    private int stockOfCyborgs;
    /**
     Each turn, every non-neutral factory produces between 0 and 3 cyborgs.
     * */
    private int production;

    public Factory(int id, Owner owner, int stockOfCyborgs, int production) {
        this.id = id;
        this.owner = owner;
        this.stockOfCyborgs = stockOfCyborgs;
        this.production = production;
    }

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
        return Integer.toString(id);
    }

}