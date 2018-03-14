package fr.univnantes.cta;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univnantes.cta.impl.AirportImpl;


public class TestAirport {
	private AirportImpl airportImpl;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAirportImplValidString() {
		this.airportImpl = new AirportImpl("Airport1");
		assert this.airportImpl.getName().equals("Airport1");
	}

}
