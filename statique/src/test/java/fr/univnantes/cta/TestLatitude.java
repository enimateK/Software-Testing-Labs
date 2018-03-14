package fr.univnantes.cta;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univnantes.cta.CompassDirection;
import fr.univnantes.cta.impl.LatitudeImpl;


public class TestLatitude {
	private LatitudeImpl latitudeImpl;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLatitudeImplGoodValues() {
		try {
			this.latitudeImpl = new LatitudeImpl(50, 50, 50, CompassDirection.NORTH);
		}catch(Exception e) {
			fail("Exception inattendue");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testLatitudeImplBadDirection() {
		this.latitudeImpl = new LatitudeImpl(50,50,50,CompassDirection.EAST);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testLatitudeImplToBigAngle() {
		this.latitudeImpl = new LatitudeImpl(200, 0,0, CompassDirection.NORTH);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testLatitudeImplNegativeAngle() {
		this.latitudeImpl = new LatitudeImpl(-30,0,0, CompassDirection.SOUTH);
	}

}
