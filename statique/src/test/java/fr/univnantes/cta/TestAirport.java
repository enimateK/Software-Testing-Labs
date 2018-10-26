package fr.univnantes.cta;


import fr.univnantes.cta.impl.AirportImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestAirport {
	private AirportImpl airportImpl;
	
	@BeforeEach
	public void setUp() throws Exception {
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testAirportImplValidString() {
		this.airportImpl = new AirportImpl("Airport1");
		assert this.airportImpl.getName().equals("Airport1");
	}

}
