package fr.univnantes.cta.impl;

import fr.univnantes.cta.Angle;
import fr.univnantes.cta.Position;
import fr.univnantes.cta.impl.*;

public class PositionImpl implements Position {

    private LatitudeImpl latitude;
    private LongitudeImpl longitude;
    private static final double EARTH_RADIUS = 6378.14;

    public PositionImpl(LatitudeImpl latitude, LongitudeImpl longitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double distanceTo(Position other) {
        return Math.acos((this.latitude.Cos()) * (this.longitude.Cos())
                * (((LatitudeImpl) other.getLatitude()).Cos())
                * (((LongitudeImpl) other.getLongitude()).Cos())
                + (this.latitude.Cos()) * (this.longitude.Sin())
                * (((LatitudeImpl) other.getLatitude()).Cos())
                * (((LongitudeImpl) other.getLongitude()).Sin())
                + (this.latitude.Sin()) * (((LatitudeImpl) 
                other.getLatitude()).Sin())) * EARTH_RADIUS;
    }

    public Angle getLatitude() {
        return latitude;
    }

    public Angle getLongitude() {
        return longitude;
    }
}
