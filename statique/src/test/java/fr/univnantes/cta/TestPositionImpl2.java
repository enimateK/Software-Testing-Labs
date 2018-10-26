package fr.univnantes.cta;

import fr.univnantes.cta.impl.LatitudeImpl;
import fr.univnantes.cta.impl.LongitudeImpl;
import fr.univnantes.cta.impl.PositionImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestPositionImpl2 {
	private PositionImpl pos;
	
	@BeforeEach
	public void setUp() throws Exception {
	}

	@AfterEach
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
