package fr.univnantes.cta;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univnantes.cta.CompassDirection;
import fr.univnantes.cta.impl.LatitudeImpl;
import fr.univnantes.cta.impl.LongitudeImpl;


public class TestLongitude {
	private LongitudeImpl longitudeImpl;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLongitudeImplGoodValues() {
		try {
			this.longitudeImpl = new LongitudeImpl(30, 30, 30, CompassDirection.WEST);
		}catch(Exception e) {
			fail("Exception inattendue");
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testLatitudeImplBadDirection() {
		this.longitudeImpl = new LongitudeImpl(50,50,50,CompassDirection.SOUTH);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testLatitudeImplToBigAngle() {
		this.longitudeImpl = new LongitudeImpl(200, 0,0, CompassDirection.WEST);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testLatitudeImplNegativeAngle() {
		this.longitudeImpl = new LongitudeImpl(-30,0,0, CompassDirection.WEST);
	}

}
