/*
 * Created on 23 oct. 06
 *
 */
package fr.univnantes.cta;

public interface Angle {

    /**
     * @return the angle, in decimal degrees
     */
    public abstract double degrees();

    /**
     * @return the angle, in radians
     */
    public abstract double radians();

    /**
     * @return the trigonometric cosine of an angle.
     */
    public abstract double cos();

    /**
     * @return the trigonometric sine of an angle.
     */
    public abstract double sin();

}