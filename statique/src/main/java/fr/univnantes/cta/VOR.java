/*
 * Created on 20 oct. 06
 *
 */
package fr.univnantes.cta;

public interface VOR {
    
    /**
     * @return the name (or code) of the VOR.
     */
    public String getName();
    
    /**
     * @return the position of the VOR.
     */
    public Position getPosition();
    
    /**
     * @param other
     * @return the distance between two VORs, in kilometers.
     */
    public double distanceTo(VOR other);
}
