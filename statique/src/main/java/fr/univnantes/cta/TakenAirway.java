/*
 * Created on 20 oct. 06
 *
 */
package fr.univnantes.cta;

/**
 * @author sunye
 * A "taken airway" is a part of a Flight Plan.
 * 
 * @see FlightPlan
 */
public interface TakenAirway {
    
    /**
     * @return the airway that will be taken by a flight.
     */
    public Airway getAirway();
    
    /**
     * @return the altitude of the flight, when taking the airway. The altitude
     * may change during a flight, but is always constant inside an airway.
     */
    public int getAltitude();
    
    /**
     * @return the sense in which the airway will be taken. 
     * @see CompassDirection
     */
    public CompassDirection getSense();
    
    
    /**
     * @return the distance of the airway.
     */
    public double distance();
}
