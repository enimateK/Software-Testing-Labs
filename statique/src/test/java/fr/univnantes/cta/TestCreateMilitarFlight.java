package fr.univnantes.cta;

import fr.univnantes.cta.impl.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestCreateMilitarFlight {
    private CreateMilitarFlight createMilitarFlight;
    private AirportImpl fromAirport, toAirport;
    private AirplaneImpl plane;
    private FlightPlanImpl flightPlan;
    private int passenger;

    @BeforeEach
    public void setUp() throws Exception {
        // cr??ation d'une configuration valide de base.
        fromAirport = new AirportImpl("fromAirport");
        toAirport = new AirportImpl("toAirport");
        plane = new AirplaneImpl(100, 6000, 30, 300);
        flightPlan = new FlightPlanImpl();
        passenger = 30;
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateMilitarFlightGoodValues() throws AirplaneOverload, IncompatibleAirway {
        this.createMilitarFlight = new CreateMilitarFlight(fromAirport, toAirport, plane, flightPlan, passenger);
    }

    @Test
    public void testCreateMilitarFlightLoadingTooBig() throws AirplaneOverload {
        passenger = 1000;

        assertThrows(AirplaneOverload.class, () -> this.createMilitarFlight = new CreateMilitarFlight(fromAirport,
                toAirport, plane, flightPlan, passenger));

    }

    @Test
    public void testCreateMilitarFlightTooLongFlight() throws IncompatibleAirway {
        AirwayImpl airway1 = new AirwayImpl(new VORImpl("d??part", new PositionStubForVOR()), new VORImpl("arriv??e", new PositionStubForVOR()));
        TakenAirwayImpl takenAirway1 = new TakenAirwayImpl(airway1, 1000, CompassDirection.NORTH);
        flightPlan.addAirway(takenAirway1);

        assertThrows(IncompatibleAirway.class, () -> this.createMilitarFlight = new CreateMilitarFlight(fromAirport,
                toAirport, plane, flightPlan, passenger));

    }

}
