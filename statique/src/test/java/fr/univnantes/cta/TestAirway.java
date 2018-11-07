package fr.univnantes.cta;


import fr.univnantes.cta.impl.AirwayImpl;
import fr.univnantes.cta.impl.VORImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestAirway {
	private AirwayImpl airwayImpl;
	
	@BeforeEach
	public void setUp() throws Exception {
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testAirwayImplGoodValues() {
		VORImpl startVOR = new VORImpl("start", new PositionStubForVOR());
		VORImpl stopVOR = new VORImpl("stop",new PositionStubForVOR());
		this.airwayImpl = new AirwayImpl(startVOR, stopVOR);
		assert this.airwayImpl.GetVORDepart().equals("start");
		assert this.airwayImpl.GetVORArrive().equals("stop");
		assert this.airwayImpl.departure().equals(startVOR);
		assert this.airwayImpl.arrival().equals(stopVOR);
	}

	/*
	 * Pas de test pour le calcul de distance, ceux-ci sont d??j??
	 * effectu??s dans la classe de test de VOR.
	 */

}
