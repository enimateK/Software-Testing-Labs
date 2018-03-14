package fr.univnantes.cta;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univnantes.cta.AirplaneOverload;
import fr.univnantes.cta.CompassDirection;
import fr.univnantes.cta.IncompatibleAirway;
import fr.univnantes.cta.impl.AirplaneImpl;
import fr.univnantes.cta.impl.AirportImpl;
import fr.univnantes.cta.impl.AirwayImpl;
import fr.univnantes.cta.impl.CreateCivilFlight;
import fr.univnantes.cta.impl.FlightPlanImpl;
import fr.univnantes.cta.impl.TakenAirwayImpl;
import fr.univnantes.cta.impl.VORImpl;


public class TestCreateCivilFlight {
	private CreateCivilFlight createCivilFlight;
	private AirportImpl fromAirport, toAirport;
	private AirplaneImpl plane;
	private FlightPlanImpl flightPlan;
	private int passenger;
	
	@Before
	public void setUp() throws Exception {
		// cr??ation d'une configuration valide de base.
		fromAirport = new AirportImpl("fromAirport");
		toAirport = new AirportImpl("toAirport");
		plane = new AirplaneImpl(100, 5000, 30, 300);
		flightPlan = new FlightPlanImpl();
		passenger = 30;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateCivilFlightGoodValues() {
		try {
			this.createCivilFlight = new CreateCivilFlight(fromAirport, toAirport, plane, flightPlan, passenger);
		}catch(AirplaneOverload e) {
			fail("AirplaneOverLoad exception inattendue");
		}
		catch(IncompatibleAirway e) {
			fail("IncompatibleAirway exception inattendue");
		}
	}
	
	@Test(expected=AirplaneOverload.class)
	public void testCreateCivilFlightLoadingTooBig() throws AirplaneOverload {
		passenger = 1000;
		try {
			this.createCivilFlight = new CreateCivilFlight(fromAirport, toAirport, plane, flightPlan, passenger);
		}catch(AirplaneOverload e ) {
			throw e;
		}catch(IncompatibleAirway e) {
			fail("IncompatibleAirway exception inattendue");
		}
	}
	
	@Test(expected=IncompatibleAirway.class)
	public void testCreateCivilFlightTooLongFlight() throws IncompatibleAirway {
		AirwayImpl airway1 = new AirwayImpl(new VORImpl("d??part", new PositionStubForVOR()), new VORImpl("arriv??e", new PositionStubForVOR()));
		TakenAirwayImpl takenAirway1 = new TakenAirwayImpl(airway1, 1000, CompassDirection.NORTH);
		flightPlan.addAirway(takenAirway1);
		try {
			this.createCivilFlight = new CreateCivilFlight(fromAirport, toAirport, plane, flightPlan, passenger);
		}catch(AirplaneOverload e) {
			fail("AirplaneOverload exception inattendue");
		}catch(IncompatibleAirway e) {
			throw e;
		}
	}

}
