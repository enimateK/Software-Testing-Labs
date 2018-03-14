package fr.univnantes.cta.impl;

import fr.univnantes.cta.Airway;
import fr.univnantes.cta.CompassDirection;
import fr.univnantes.cta.TakenAirway;

public class TakenAirwayImpl implements TakenAirway {

    private Airway airway;
    private int altitude;
    private CompassDirection compassDirection;

    public TakenAirwayImpl(Airway airway, int altitude, CompassDirection dir) {
        this.airway = airway;
        this.altitude = altitude;
        compassDirection = dir;
    }

    public double distance() {
        return airway.getDistance();
    }

    public Airway getAirway() {
        return airway;
    }

    public int getAltitude() {
        return altitude;
    }

    public CompassDirection getSense() {
        return compassDirection;
    }
}
