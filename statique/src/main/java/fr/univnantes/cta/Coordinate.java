/*
 * Created on 22 oct. 06
 *
 */
package fr.univnantes.cta;

/**
 * @author sunye
 *
 */

public interface Coordinate extends Angle {

    /**
     * @return the location of the coordinate. North or South, if 
     * it represents a Latitude. East or West, if it represents a
     * Longitude. 
     * @see CompassDirection
     */
    public abstract CompassDirection direction();
}