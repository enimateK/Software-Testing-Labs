package fr.univnantes.cta;


import fr.univnantes.cta.impl.LongitudeImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestLongitude {
    private LongitudeImpl longitudeImpl;


    @Test
    public void testLongitudeImplGoodValues() {
        this.longitudeImpl = new LongitudeImpl(30, 30, 30, CompassDirection.WEST);
    }

    @Test
    public void testLatitudeImplBadDirection() {
        assertThrows(IllegalArgumentException.class, () -> this.longitudeImpl = new LongitudeImpl(50, 50, 50, CompassDirection.SOUTH));
    }

    @Test
    public void testLatitudeImplToBigAngle() {
        assertThrows(IllegalArgumentException.class, () -> this.longitudeImpl = new LongitudeImpl(200, 0, 0, CompassDirection.WEST));
    }

    @Test
    public void testLatitudeImplNegativeAngle() {
        assertThrows(IllegalArgumentException.class, () -> this.longitudeImpl = new LongitudeImpl(-30, 0, 0, CompassDirection.WEST));
    }

}
