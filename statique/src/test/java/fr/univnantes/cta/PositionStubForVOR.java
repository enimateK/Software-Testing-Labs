package fr.univnantes.cta;

import fr.univnantes.cta.CompassDirection;
import fr.univnantes.cta.Position;
import fr.univnantes.cta.impl.LatitudeImpl;
import fr.univnantes.cta.impl.LongitudeImpl;
import fr.univnantes.cta.impl.PositionImpl;


public class PositionStubForVOR extends PositionImpl{

	public PositionStubForVOR() {
		// constructeur inutile, juste pour ??viter une erreur Java
		// (obligation d'appeler super dans une sous classe)
		super(new LatitudeImpl(30, 30, 30, CompassDirection.NORTH), new LongitudeImpl(30,30,30, CompassDirection.WEST));
	}
	
	public double distanceTo(Position other) {
		return 42;
	}
}
