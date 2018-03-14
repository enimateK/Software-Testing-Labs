package fr.univnantes.cta.impl;

import fr.univnantes.cta.Airplane;
import fr.univnantes.cta.AirplaneOverload;
import fr.univnantes.cta.Airport;
import fr.univnantes.cta.Flight;
import fr.univnantes.cta.FlightPlan;
import fr.univnantes.cta.IncompatibleAirway;

abstract class FlightImpl implements Flight {

    private Airport departure, arrival;
    private Airplane airplane;
    private FlightPlan flightPlane;
    protected int passengers;

    public FlightImpl(Airport departure, Airport arrival,
            Airplane plane, FlightPlan fp, int passengers)
            throws IncompatibleAirway, AirplaneOverload {

        this.departure = departure;
        this.arrival = arrival;
        this.airplane = plane;
        this.flightPlane = fp;
        this.passengers = passengers;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public Airport getArrival() {
        return arrival;
    }

    public Airport getDeparture() {
        return departure;
    }

    public FlightPlan getFlightPlan() {
        return flightPlane;
    }

    public abstract double loadingWeight();

    public abstract double totalWeight();
}