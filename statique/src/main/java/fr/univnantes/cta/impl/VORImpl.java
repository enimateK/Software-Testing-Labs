package fr.univnantes.cta.impl;

import fr.univnantes.cta.Position;
import fr.univnantes.cta.VOR;

public class VORImpl implements VOR {

    private String name;
    private Position position;

    public VORImpl(String name, Position position) {
        this.name = name;
        this.position = position;
    }

    public double distanceTo(VOR other) {
        return (this.getPosition()).distanceTo(other.getPosition());
    }

    public String getName() {
        return this.name;
    }

    public Position getPosition() {
        return position;
    }
}
