package fr.univnantes.cta;

import fr.univnantes.cta.impl.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestFlightPlan {
	private FlightPlanImpl flightPlanImpl;
	private TakenAirwayImpl firstTakenAirway;
	private TakenAirwayImpl secondTakenAirway;
	private VORImpl VORStop = new VORImpl("stop", new PositionImpl(new LatitudeImpl(40, 30, 30, CompassDirection.NORTH), new LongitudeImpl(40,30,30, CompassDirection.WEST)));
	
	@BeforeEach
	public void setUp() throws Exception {
		this.flightPlanImpl = new FlightPlanImpl();
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	private void build1TakenAirwayPlan() {
		VORImpl VORStart = new VORImpl("start", new PositionImpl(new LatitudeImpl(30, 30, 30, CompassDirection.NORTH), new LongitudeImpl(30,30,30, CompassDirection.WEST)));
		AirwayImpl airway = new AirwayImpl(VORStart, VORStop);
		firstTakenAirway = new TakenAirwayImpl(airway, 30, CompassDirection.NORTH);
		this.flightPlanImpl.addAirway(firstTakenAirway);
	}

	@Test
	public void testAddAirway1() {
		build1TakenAirwayPlan();
		assert this.flightPlanImpl.getTakenAirways().get(0).equals(firstTakenAirway);
	}

	@Test
	public void testAddAirway2Correct() {
		build1TakenAirwayPlan();
		
		VORImpl VORC = new VORImpl("c", new PositionImpl(new LatitudeImpl(50, 30, 30, CompassDirection.NORTH), new LongitudeImpl(50,30,30, CompassDirection.WEST)));
		AirwayImpl airway2 = new AirwayImpl(VORStop, VORC);
		TakenAirwayImpl secondTakenAirway = new TakenAirwayImpl(airway2, 30, CompassDirection.NORTH);
		this.flightPlanImpl.addAirway(secondTakenAirway);
		assert flightPlanImpl.getTakenAirways().get(1).equals(secondTakenAirway);
	}

    @Test
    public void testAddAirway2NotCorrect() {
        build1TakenAirwayPlan();

        VORImpl VORC = new VORImpl("c", new PositionStubForVOR());
        VORImpl VORD = new VORImpl("d", new PositionStubForVOR());
        AirwayImpl airway2 = new AirwayImpl(VORC, VORD);
        TakenAirwayImpl secondTakenAirway = new TakenAirwayImpl(airway2, 30, CompassDirection.NORTH);

        assertThrows(IllegalArgumentException.class, () ->
                this.flightPlanImpl.addAirway(secondTakenAirway));
    }
	
	@Test
	public void testDistanceNotNegative() {
		build1TakenAirwayPlan();
		
		VORImpl VORC = new VORImpl("c", new PositionImpl(new LatitudeImpl(50, 30, 30, CompassDirection.NORTH), new LongitudeImpl(50,30,30, CompassDirection.WEST)));
		AirwayImpl airway2 = new AirwayImpl(VORStop, VORC);
		TakenAirwayImpl secondTakenAirway = new TakenAirwayImpl(airway2, 30, CompassDirection.NORTH);
		this.flightPlanImpl.addAirway(secondTakenAirway);
		double dist = this.flightPlanImpl.distance();
		assert dist > 0.0;
	}

	@Test
	public void testGetPath() {
		build1TakenAirwayPlan();
		
		VORImpl VORC = new VORImpl("c", new PositionStubForVOR());
		AirwayImpl airway2 = new AirwayImpl(VORStop, VORC);
		TakenAirwayImpl secondTakenAirway = new TakenAirwayImpl(airway2, 30, CompassDirection.NORTH);
		this.flightPlanImpl.addAirway(secondTakenAirway);
		List<TakenAirway> path = this.flightPlanImpl.getPath();
		System.out.println(path.size());
		assert path.contains(firstTakenAirway);
		assert path.contains(secondTakenAirway);
	}

}
