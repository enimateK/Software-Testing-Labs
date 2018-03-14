package fr.univnantes.cta;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univnantes.cta.CompassDirection;
import fr.univnantes.cta.impl.AirwayImpl;
import fr.univnantes.cta.impl.TakenAirwayImpl;
import fr.univnantes.cta.impl.VORImpl;


public class TestTakenAirway {
	private TakenAirwayImpl takenAirwayImpl;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTakenAirwayImplGoodValues() {
		VORImpl VORStart = new VORImpl("start", new PositionStubForVOR());
		VORImpl VORStop = new VORImpl("stop", new PositionStubForVOR());
		AirwayImpl airway = new AirwayImpl(VORStart, VORStop);
		this.takenAirwayImpl = new TakenAirwayImpl(airway, 30, CompassDirection.NORTH);
		assert this.takenAirwayImpl.getAltitude() == 30;
		assert this.takenAirwayImpl.getSense() == CompassDirection.NORTH;
		assert this.takenAirwayImpl.getAirway().equals(airway);
	}

	/*
	 * On ne teste pas le calcul de la distance, il est d??j?? calcul??
	 * dans la classe airway.
	 */

}
