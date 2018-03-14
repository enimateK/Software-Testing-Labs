package fr.univnantes.cta.impl;

import fr.univnantes.cta.CompassDirection;

public class LongitudeImpl extends CoordinateImpl {

    public LongitudeImpl(int angle, int minute, int seconde, CompassDirection cd) {
        super(angle, minute, seconde, cd);

        if ((cd != CompassDirection.EAST) && (cd != CompassDirection.WEST)) {
            throw new IllegalArgumentException("Wrong Direction");
        }

        if ((this.degrees() < 0) || (this.degrees() > 180)) {
            throw new IllegalArgumentException("Wrong Angle");
        }

    }
}
