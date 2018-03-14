/*
 * Created on 18 oct. 06
 *
 */
package fr.univnantes.cta;

import java.util.List;

public interface FlightPlan {
    
    /**
     * @return an unmodificable list, containing all airways
     * used in the flight plan.
     * 
     * @see java.util.Collections#unmodifiableList(List)
     */
    public List<TakenAirway> getPath();
    
    /**
     * Adds an airway to the flight plan
     * @param a the airway
     */
    public void addAirway(TakenAirway a);
    
    /**
     * @return the total distance of the flight plan, the sum
     * of all airways, in km.
     */
    public double distance();
}
