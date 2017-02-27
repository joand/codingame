package fr.joand.model;

import java.util.Objects;

public class Factory {
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