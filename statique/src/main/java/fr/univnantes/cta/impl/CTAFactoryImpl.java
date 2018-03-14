package fr.univnantes.cta.impl;

import fr.univnantes.cta.Airplane;
import fr.univnantes.cta.AirplaneOverload;
import fr.univnantes.cta.Airport;
import fr.univnantes.cta.Airway;
import fr.univnantes.cta.CTAFactory;
import fr.univnantes.cta.CompassDirection;
import fr.univnantes.cta.Coordinate;
import fr.univnantes.cta.Flight;
import fr.univnantes.cta.FlightPlan;
import fr.univnantes.cta.IncompatibleAirway;
import fr.univnantes.cta.Position;
import fr.univnantes.cta.TakenAirway;
import fr.univnantes.cta.VOR;

import java.util.Arrays;

public class CTAFactoryImpl implements CTAFactory {

    private Airplane[] airplanes;

    public Airplane createAirplane(double weight, int loading, int consumption, int tank) {
        return new AirplaneImpl(weight, loading, consumption, tank);
    }

    public Airport createAirport(String name) {
        return new AirportImpl(name);
    }

    public Airway createAirway(VOR start, VOR stop) {
        return new AirwayImpl(start, stop);
    }

    public Flight createCivilFlight(Airport departure, Airport arrival, 
            Airplane plane, FlightPlan fp, int passengers) 
            throws IncompatibleAirway, AirplaneOverload {
        return new CreateCivilFlight(departure, arrival, plane, fp, passengers);
    }

    public FlightPlan createFlightPlan() {
        return new FlightPlanImpl();
    }

    public Coordinate createLatitude(int degrees, int minutes, int seconds, 
            CompassDirection dir) {
        return new LatitudeImpl(degrees, minutes, seconds, dir);
    }

    public Coordinate createLongitude(int degrees, int minutes, int seconds, 
            CompassDirection dir) {
        return new LongitudeImpl(degrees, minutes, seconds, dir);
    }

    public Flight createMilitarFlight(Airport departure, Airport arrival, 
            Airplane plane, FlightPlan fp, int passengers) 
            throws IncompatibleAirway, AirplaneOverload {
        return new CreateMilitarFlight(departure, arrival, plane, fp, passengers);
    }

    public Position createPosition(Coordinate latitude, Coordinate longitude) {
        return new PositionImpl((LatitudeImpl) latitude, (LongitudeImpl) longitude);
    }

    public TakenAirway createTakenAirway(Airway airway, int altitude, 
            CompassDirection dir) {
        return new TakenAirwayImpl(airway, altitude, dir);
    }

    public VOR createVOR(String name, Position position) {
        return new VORImpl(name, position);
    }

    public void setAirplanes(Airplane[] array) {
        airplanes = Arrays.copyOf(array,array.length);
    }
}
