package fr.nantes.alarm;

import javax.annotation.Nonnegative;

/**
 * Factory for creating AlarmClock instances.
 */
public class AlarmClockFactory {

    /**
     * Instantiates a new AlarmClock and initializes it with the following parameters:
     *
     * @param ring choice of the ringtone (value between 1 and 10)
     * @param hour the alarm hour (value between 0 and 23).
     * @param min the alarm minute (value between 0 and 59).
     *
     * @return an instance of AlarmClock
     * @throws AlarmClockException when arguments are invalid
     */
    public AlarmClock createAlarmClock(@Nonnegative int ring, @Nonnegative int hour,
                                       @Nonnegative int min) throws AlarmClockException {
        return new BasicAlarmClock(ring, hour, min);
    }
}
