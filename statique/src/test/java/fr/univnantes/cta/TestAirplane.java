package fr.univnantes.cta;



import fr.univnantes.cta.impl.AirplaneImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestAirplane {
	private AirplaneImpl airplaneImpl;
	
	@BeforeEach
	public void setUp() throws Exception {
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testAirplaneImpl() {
		this.airplaneImpl = new AirplaneImpl(100, 50, 40, 30);
		assert this.airplaneImpl.weight() == 100;
		assert this.airplaneImpl.getLoading() == 50;
		assert this.airplaneImpl.getConsumption() == 40;
		assert this.airplaneImpl.getTank() == 30;
	}

	@Test
	public void testGetAutonomy() {
		this.airplaneImpl = new AirplaneImpl(100,50,40,30);
		assert this.airplaneImpl.getAutonomy() == 30/40;
	}

	/*
	 * Pas de test pour loading et weight : simple getters
	 */

}
