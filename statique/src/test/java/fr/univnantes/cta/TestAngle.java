package fr.univnantes.cta;

import fr.univnantes.cta.impl.AngleImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestAngle {
	private AngleImpl angleImpl;



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
