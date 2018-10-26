package fr.univnantes.cta;

import fr.univnantes.cta.impl.VORImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestVOR {
	private VORImpl vorImpl;
	
	@BeforeEach
	public void setUp() throws Exception {
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testVORImpl() {
		PositionStubForVOR pos = new PositionStubForVOR();
		vorImpl = new VORImpl("name", pos);
		assert vorImpl.getName().equals("name");
		assert vorImpl.getPosition().equals(pos);
	}

	@Test
	public void testDistanceToPositive() {
		PositionStubForVOR fromPosition = new PositionStubForVOR();
		PositionStubForVOR otherPosition = new PositionStubForVOR();
		vorImpl = new VORImpl("name", fromPosition);
		double dist = vorImpl.distanceTo(new VORImpl("name2", otherPosition));
		assert dist > 0.0;
	}
}
