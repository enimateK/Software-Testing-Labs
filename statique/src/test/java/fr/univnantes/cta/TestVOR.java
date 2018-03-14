package fr.univnantes.cta;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univnantes.cta.impl.VORImpl;


public class TestVOR {
	private VORImpl vorImpl;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
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
