package edu.psu.teamone.main;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.ArrayList;

public class Meeting {
	private boolean[] days = new boolean[7];
	private Time startTime;
	private Time stopTime;

	Meeting(boolean[] days, Time startTime, Time stopTime) {
		this.days = days;
		this.startTime = startTime;
		this.stopTime = stopTime;
	}

	public boolean[] getDays() {
		return days;
	}

	public Time getStartTime() {
		return startTime;
	}

	public Time getStopTime() {
		return stopTime;
	}
}
