package fr.univnantes.cta.impl;

import fr.univnantes.cta.Angle;
import fr.univnantes.cta.Position;

public class PositionImplOld implements Position {

    private LatitudeImpl latitude;
    private LongitudeImpl longitude;
    private static final double EARTH_RADIUS = 6378.14;

    public PositionImplOld(LatitudeImpl latitude, LongitudeImpl longitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }



    public Angle getLatitude() {
        return latitude;
    }

    public Angle getLongitude() {
        return longitude;
    }

    public double distanceTo(Position other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
