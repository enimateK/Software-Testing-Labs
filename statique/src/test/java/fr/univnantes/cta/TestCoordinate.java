package fr.univnantes.cta;

import static org.junit.Assert.*;

import fr.univnantes.cta.impl.CoordinateImpl;
import org.junit.jupiter.api.Test;


public class TestCoordinate {
	private CoordinateImpl coord;
	



	@Test
	public void testCos() {
		coord = new CoordinateImpl(90, 0, 0, CompassDirection.SOUTH);
		assertEquals(0, coord.Cos(), 1);
	}
	
	@Test
	public void testCosEast() {
		coord = new CoordinateImpl(90,0,0,CompassDirection.EAST);
		assertEquals(0, coord.Cos(), 1);
	}

	@Test
	public void testSin() {
		coord = new CoordinateImpl(90, 0, 0, CompassDirection.SOUTH);
		assertEquals(-1, coord.Sin(),1);
	}
	
	@Test
	public void testSinEast() {
		coord = new CoordinateImpl(90,0,0,CompassDirection.EAST);
		assert coord.Sin() == 1;
	}

}
