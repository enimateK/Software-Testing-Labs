package fr.univnantes.cta.impl;

import fr.univnantes.cta.CompassDirection;
import fr.univnantes.cta.Coordinate;

public class CoordinateImplOld extends AngleImpl implements Coordinate {

    public CoordinateImplOld(int a, int m, int s, CompassDirection cd) {
        super(a, m, s);
    }

    public CompassDirection direction() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double Cos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double Sin() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
