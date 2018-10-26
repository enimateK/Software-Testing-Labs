package fr.univnantes.cta;

import fr.univnantes.cta.impl.LatitudeImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestLatitude {
    private LatitudeImpl latitudeImpl;

    @Test
    public void testLatitudeImplGoodValues() {
        this.latitudeImpl = new LatitudeImpl(50, 50, 50, CompassDirection.NORTH);
    }

    @Test
    public void testLatitudeImplBadDirection() {

        assertThrows(IllegalArgumentException.class, () -> this.latitudeImpl = new LatitudeImpl(50, 50, 50, CompassDirection.EAST));
    }

    @Test
    public void testLatitudeImplToBigAngle() {
        assertThrows(IllegalArgumentException.class, () -> this.latitudeImpl = new LatitudeImpl(200, 0, 0, CompassDirection.NORTH));

    }

    @Test
    public void testLatitudeImplNegativeAngle() {
        assertThrows(IllegalArgumentException.class, () -> this.latitudeImpl = new LatitudeImpl(-30, 0, 0, CompassDirection.SOUTH));
    }

}
