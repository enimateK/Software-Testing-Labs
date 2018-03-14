package fr.nantes.alarm;

import javax.annotation.Nonnegative;
import java.util.Objects;

/**
 * Basic implementation of the {@link AlarmClock} interface.
 *
 * Initial code from Arnaud Lanoix and Jean-Marie Mottu
 */
public class BasicAlarmClock implements AlarmClock {

    /**
     * An {@link int} value between 1 and 10 representing the ringtone.
     */
    @Nonnegative
	private int ring;

    /**
     * An {@link int} value between 0 and 23 representing the alarm hour.
     */
    @Nonnegative
	private int hour;

    /**
     * An {@link int} value between 0 and 59 representing the alarm minutes.
     */
    @Nonnegative
	private int min;

    /**
     * A {@link boolean} value: true if the alarm is enabled, false otherwise.
     */
	private boolean active = false;

	/**
	 * A {@link boolean} value: true if the alarm is ringing, false otherwise.
	 */
	private boolean ringing = false;

    /**
     * {@see AlarmClock}
     * @param ring {@link BasicAlarmClock#ring}
     * @param hour {@link BasicAlarmClock#hour}
     * @param min {@link BasicAlarmClock#min}
     * @throws AlarmClockException if one parameter is out of bounds.
     */
	protected BasicAlarmClock(int ring, int hour, int min) throws AlarmClockException {
		if (hour < 0) {
			throw new AlarmClockException("bad hour: inf value");
		} else if (hour > 23) {
			throw new AlarmClockException("bad hour: sup value");
		} else if (min < 0) {
			throw new AlarmClockException("bad min: inf value");
		} else if (min > 59) {
			throw new AlarmClockException("bad min: sup value");
		} else if ((ring < 1) || (ring > 10)) {
			throw new AlarmClockException("bad ringtone: out of limits");
		} else {
			this.ring = ring;
			this.hour = hour;
			this.min = min;
		}
	}

    /**
     * {@inheritDoc}
     */
	public boolean selectRing(int ringtone) throws AlarmClockException {
		if (!ringing) {
			if ((ringtone < 1) || (ringtone > 10)) {
				throw new AlarmClockException("bad ringtone: out of bounds");
			}
			this.ring = ringtone;
			return true;
		}else{
			return false;
		}
	}

	/**
     * {@inheritDoc}
	 */
	public void addMin(int minutes) throws AlarmClockException {
		int addedHour = 0;
		int newMin = 0;
		if (minutes < 0) {
			throw new AlarmClockException("Cannot add negative minutes");
		}
		while (minutes > 59) {
			addedHour++;
			minutes = minutes - 60;
		}
		newMin = this.min + minutes;
		if (newMin > 59) {
			addedHour++;
			newMin = newMin - 60;
		}

		this.hour =(this.hour + addedHour) % 24;
		this.min = newMin;
	}

    /**
     * {@inheritDoc}
     */
	public void activate() {
		if(!ringing){
			this.active = !this.active;
			if (this.active) {
				Calendar cal = new Calendar();
				if (this.hour == cal.get(Calendar.HOUR))
					if (this.min == cal.get(Calendar.MINUTE))
						ringing = true;
			}
		}

	}


    /**
     * {@inheritDoc}
     */
	public void switchOff(boolean snooze) throws AlarmClockException {
		this.ringing = false;
		if (snooze){
			this.active = true;
			this.addMin(5);
		}
	}

    @Override
    public int hashCode() {
        return Objects.hash(ring, hour, min, active, ringing);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicAlarmClock that = (BasicAlarmClock) o;
        return ring == that.ring &&
                hour == that.hour &&
                min == that.min &&
                active == that.active &&
                ringing == that.ringing;
    }

	/**
	 * {@inheritDoc}
	 */
	public boolean isActive() {
		return this.active;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isRinging() {
		return this.ringing;
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRing() {
        return this.ring;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHour() {
        return this.hour;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMin() {
        return this.min;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setRingingOn() {
        this.ringing = true;
        this.active = false;
    }


    @Override
    public String toString() {
        return "BasicAlarmClock{" +
                "ring=" + ring +
                ", hour=" + hour +
                ", min=" + min +
                ", active=" + active +
                ", ringing=" + ringing +
                '}';
    }

	public static void main(String args[]) {
		try {
			BasicAlarmClock ac = new BasicAlarmClock(3, 12, 30);
			ac.activate();
			System.out.println(ac);
			ac.addMin(55);
			ac.activate();
			System.out.println(ac);
		} catch (AlarmClockException e) {
			System.out.println(e);
		}
	}
}