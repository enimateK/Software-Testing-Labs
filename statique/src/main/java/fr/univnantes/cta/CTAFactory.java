/*
 * Created on 23 oct. 06
 *
 */
package fr.univnantes.cta;

public interface CTAFactory {

    /**
     * @param weight
     * @param loading
     * @param consumption
     * @param tank
     * @return a new airplane
     */
    public Airplane createAirplane(double weight, int loading, int consumption, int tank);
    
    /**
     * @param name
     * @return a new airport
     */
    public Airport createAirport(String name);
    
    /**
     * @param start
     * @param stop
     * @return a new airway
     */
    public Airway createAirway(VOR start, VOR stop);
    
    public FlightPlan createFlightPlan();
    
    
    /**
     * @param departure
     * @param arrival
     * @param plane
     * @param fp
     * @param passengers
     * @return
     * @throws IncompatibleAirway
     */
    public Flight createCivilFlight(Airport departure, Airport arrival,
            Airplane plane, FlightPlan fp, int passengers)
            throws IncompatibleAirway, AirplaneOverload;
    
    /**
     * @param departure
     * @param arrival
     * @param plane
     * @param fp
     * @param passengers
     * @return
     * @throws IncompatibleAirway
     */
    public Flight createMilitarFlight(Airport departure, Airport arrival,
            Airplane plane, FlightPlan fp, int passengers)
            throws IncompatibleAirway, AirplaneOverload;

    
    /**
     * @param degrees
     * @param minutes
     * @param seconds
     * @param dir
     * @return
     */
    public Coordinate createLatitude(int degrees, int minutes,
            int seconds, CompassDirection dir);
    
    /**
     * @param degrees
     * @param minutes
     * @param seconds
     * @param dir
     * @return
     */
    public Coordinate createLongitude(int degrees, int minutes,
            int seconds, CompassDirection dir);

    /**
     * @param latitude
     * @param longitude
     * @return
     */
    public Position createPosition(Coordinate latitude, Coordinate longitude);
    
    /**
     * @param airway
     * @param altitude
     * @param dir
     * @return
     */
    public TakenAirway createTakenAirway(Airway airway, int altitude, CompassDirection dir);
    
    /**
     * @param name
     * @param position
     * @return
     */
    public VOR createVOR(String name, Position position);
}
