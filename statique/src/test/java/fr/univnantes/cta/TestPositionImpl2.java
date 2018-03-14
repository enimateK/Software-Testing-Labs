package fr.univnantes.cta;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univnantes.cta.CompassDirection;
import fr.univnantes.cta.impl.LatitudeImpl;
import fr.univnantes.cta.impl.LongitudeImpl;
import fr.univnantes.cta.impl.PositionImpl;


public class TestPositionImpl2 {
	private PositionImpl pos;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	// Test de la m??thode de distances
	@Test
	public void testDistanceTo() {
		this.pos = new PositionImpl(new LatitudeImpl(30, 0, 0, CompassDirection.NORTH), new LongitudeImpl(40, 0,0, CompassDirection.WEST));
		PositionImpl pos2 = new PositionImpl(new LatitudeImpl(40, 0, 0, CompassDirection.NORTH), new LongitudeImpl(70, 0,0, CompassDirection.WEST));
		assert pos.distanceTo(pos2) > 2935 && pos.distanceTo(pos2) < 2936;
	}
	
	@Test
	public void testDistanceReverse() {
		this.pos = new PositionImpl(new LatitudeImpl(30, 0, 0, CompassDirection.NORTH), new LongitudeImpl(40, 0,0, CompassDirection.WEST));
		PositionImpl pos2 = new PositionImpl(new LatitudeImpl(40, 0, 0, CompassDirection.NORTH), new LongitudeImpl(70, 0,0, CompassDirection.WEST));
		assert pos.distanceTo(pos2) == pos2.distanceTo(pos);
	}

}
