package fr.nantes.alarm;

import javax.annotation.Nonnegative;

/**
 *
 * Common interface for simple alarm clocks.
 *
 * Client can choose a ringtone and the alarm time (hour and minutes).
 *
 * The alarm clock must be activated every day to ring  at the expected hour.
 * That is, once the alarm clock rings, it becomes inactive.
 *
 *
 * Created on 16/11/2017.
 * Initial code form Arnaud Lanoix and Jean-Marie Mottu
 */
public interface AlarmClock {

    /**
     * Selects a new ringtone, if and only if this alarm clock is not ringing.
     *
     * @param ringtone the ringtone, between 1 and 10.
     * @return true when the ringtone could have been changed.
     * @throws AlarmClockException when the ringtone is invalid.
     */
    boolean selectRing(@Nonnegative int ringtone) throws AlarmClockException;

    /**
     * Increments the ringing time with the number of minutes passed as parameter.
     * As a result, it changes the Hour and the Minutes previsously stored.
     *
     * @param minutes the increment, in minutes.
     * @throws AlarmClockException if the parameter is invalid.
     */
     void addMin(@Nonnegative int minutes) throws AlarmClockException;


    /**
     * Activates or disables (makes inactive) this Alarm Clock.
     *
     * The Alarm Clock only rings if it is active.
     */
    void activate();


    /**
     * Switches off (stops) this alarm clock ring. If the parameter snooze is true, makes it active again and rings
     * back after 5 minutes.
     *
     * @param snooze when true, makes the alarm clock ring again, 5 minutes later.
     * @throws AlarmClockException if the alarm cannot be snoozed.
     */
    void switchOff(boolean snooze) throws AlarmClockException;

    /**
     * Accessor for the current status of this alarm, if it is enabled or not.
     *
     * @return a {@link boolean} value: true if the alarm is enabled, false otherwise.
     */
    boolean isActive();

    /**
     * Returns the current state of this alarm.
     *
     * @return a {@link boolean} value: true if the alarm is ringing, false otherwise.
     */
    boolean isRinging();

    /**
     * Accessor to this alarm ringtone.
     *
     * @return an {@link int} value between 1 and 10.
     */
    @Nonnegative
    int getRing();

    /**
     * Accessor to this alarm hour.
     *
     * @return an {@link int} value between 0 and 23.
     */
    @Nonnegative
    int getHour();

    /**
     * Accessor to this alarm's minutes.
     *
     * @return an {@link int} value between 0 and 59.
     */
    @Nonnegative
    int getMin();
    
    /**
     * Setter to switch on this.ringing attribute for testing.
     * Desactivate the alarmClock to prevent inconsistency
     */
    void setRingingOn();
}