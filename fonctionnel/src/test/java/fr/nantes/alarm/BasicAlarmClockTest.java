package fr.nantes.alarm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BasicAlarmClockTest {

    private AlarmClock alarm;

    @BeforeEach
    void setUp() throws AlarmClockException {
        this.alarm = new AlarmClockFactory().createAlarmClock(1, 18, 29);
    }
    
}