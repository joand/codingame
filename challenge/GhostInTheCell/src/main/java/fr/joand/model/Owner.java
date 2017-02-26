package fr.joand.model;


public enum Owner {
    ally(1), enemy(-1), neutral(0);

    final int id;

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
