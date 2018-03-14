package fr.univnantes.cta;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import fr.univnantes.cta.impl.AngleImpl;


public class TestAngle {
	private AngleImpl angleImpl;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAnglePositiveValue() {
		this.angleImpl = new AngleImpl(34,31,48);
		assert this.angleImpl.degrees() == 34.53;
	}
	
	@Test
	public void testAngleNegativeValue() {
		this.angleImpl = new AngleImpl(-34, -31, -48);
		assertEquals(-33.47, this.angleImpl.degrees(), 2);
	}
	
	/*
	 * Seule la m??thode degrees est calcul??e localement, j'ai
	 * d??cid?? de ne pas tester les m??thodes associ??es ?? des fonctions
	 * de la classe Math Java, certains testeurs l'ont surement mieux
	 * test??e que moi.
	 */
}
