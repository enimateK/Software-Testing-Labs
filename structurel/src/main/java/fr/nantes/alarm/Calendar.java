package fr.nantes.alarm;

import java.util.GregorianCalendar;

public class Calendar {

	public final static int HOUR = 1;
	public final static int MINUTE = 2;

	public Calendar() {
		super();
		System.out.println("A Calendar is instantiated");
	}

	public int get(int field) {
		System.out.println("Calendar consulted");
		GregorianCalendar calendar =
				new  GregorianCalendar();
		if (field == HOUR)
			return calendar.get(GregorianCalendar.HOUR);
		else if (field == MINUTE)
			return calendar.get(GregorianCalendar.MINUTE);
		else
			return -1;
	}
}
