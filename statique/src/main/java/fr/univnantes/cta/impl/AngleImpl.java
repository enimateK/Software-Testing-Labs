package fr.univnantes.cta.impl;

import fr.univnantes.cta.Angle;

public class AngleImpl implements Angle {

    private double theta;

    public AngleImpl(int degre, int minute, int seconde) {
        theta = degre + (minute + seconde / 60.) / 60.;
    }

    public double cos() {
        return Math.cos(radians());
    }

    public double degrees() {
        return theta;
    }

    public double radians() {
        return Math.toRadians(theta);
    }

    public double sin() {
        return Math.sin(radians());
    }
}
