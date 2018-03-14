/*
 * Created on 22 oct. 06
 *
 */
package fr.univnantes.cta;

/**
 * @author sunye
 * Represents a position on earth.
 */
public interface Position {

    /**
     * @return the latitude of this position.
     */
    public abstract Angle getLatitude();

    /**
     * @return the longitude of this position.
     */
    public abstract Angle getLongitude();

    /**
     * Finds the distance in kilometers between two positions on earth.
     * 
     * @param other a Position
     * @return the distance between two positions, in km
     */
    public abstract double distanceTo(Position other);

}