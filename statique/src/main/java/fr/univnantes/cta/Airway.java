/*
 * Created on 18 oct. 06
 *
 */
package fr.univnantes.cta;


public interface Airway {
    /**
     * @return the distance between the two {@link VOR VORs} 
     * that delimits the Airway.
     */
    public double getDistance();
    
    public VOR departure();
    
    public VOR arrival();
}
