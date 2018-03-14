package fr.univnantes.cta;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univnantes.cta.impl.AirplaneImpl;


public class TestAirplane {
	private AirplaneImpl airplaneImpl;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
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
