package fr.nantes.alarm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicAlarmClockTest {

    private AlarmClock alarm;

    @BeforeEach
    void setUp() throws AlarmClockException {
        this.alarm = new AlarmClockFactory().createAlarmClock(1, 18, 29);
    }

    /**
     * Test de la méthode enable() quand AlarmCLock est dans l'état "ringing"
     */
    @Test
    void testEnableWhenRinging() {
    }

    /**
     * Test de la méthode enable() quand AlarmCLock est dans l'état "not ringing"
     */
    @Test
    void testEnableWhenNotRinging() {
    }
}