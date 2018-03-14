/*
 * Created on 18 oct. 06
 *
 */
package fr.univnantes.cta;

/**
 * @author sunye
 * 
 * Represents a flight. Every flight has a flight plan, a airplane, 
 *
 */
public interface Flight {
    
    /**
     * @return the airplane that realizes this flight.
     */
    public Airplane getAirplane();
    
    /**
     * @return The flight plan associated to this flight.
     * Every flight must have a flight plan.
     */
    public FlightPlan getFlightPlan();
    
    /**
     * @return The departure Airport.
     * @see Airport
     */
    public Airport getDeparture();
    
    /**
     * @return the arrival airport.
     * @see Airport
     */
    public Airport getArrival();
    
    /**
     * @return the weight of the loading, that depends on the kind of flight.
     */
    public double loadingWeight(); 
    
    /**
     * @return the total weight of this flight: the weight of the empty airplane, plus
     * the weight of the passengers.
     */
    public double totalWeight(); 
   
 }
