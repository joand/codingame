package fr.joand.model;

/**
 * Created by user on 25/02/2017.
 */
public enum Owner {
    ally(1), enemy(-1), neutral(0);

    int id;

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
