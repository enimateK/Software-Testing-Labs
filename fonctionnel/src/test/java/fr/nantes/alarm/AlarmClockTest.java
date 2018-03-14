package fr.nantes.alarm;

import org.junit.Test;

/**
 * Created on 16/11/2017.
 */
public class AlarmClockTest {

    AlarmClockFactory factory = new AlarmClockFactory();


    @Test
    public void testAlarmClockCreation() throws AlarmClockException {
        AlarmClock alarmClock = factory.createAlarmClock(1, 12, 30);
    }


}
