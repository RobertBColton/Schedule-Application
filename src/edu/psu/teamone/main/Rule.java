package edu.psu.teamone.main;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map.Entry;

public abstract class Rule {

	// Represents a schedule that is impossible to
	// realize. For example, an instructor scheduled
	// for two simultaneous classes.
	public final static int IMPOSSIBLE = 1000000;

	// This operation returns a fitness score for the
	// provided schedule. Higher values indicate a less
	// fit schedule. A value of 0 indicates a perfect fit.
	public abstract int getFitness(Schedule schedule);

	public class SpecificRule extends Rule {
		// This operation returns a fitness score for the
		// provided schedule. Higher values indicate a less
		// fit schedule. A value of 0 indicates a perfect fit.
		public int getFitness(Schedule schedule) {
			return 0;
		}
	}

	public class InstructorConflict extends Rule {
		@Override
		// This operation returns 0 if no instructors
		// are scheduled for simultaneous classes and
		// IMPOSSIBLE otherwise.
		public int getFitness(Schedule schedule) {
			ArrayList<Meeting> meetingList = new ArrayList<Meeting>();
			for (Entry<Section, Meeting> scheduleEntry : schedule.getSections().entrySet()) {
				// Put all meeting in schedule to meetingList
				meetingList.add(scheduleEntry.getValue());
			}
			boolean checkConflicts = checkConflicts(meetingList);
			int score = 0;
			if (!checkConflicts) {
				// each back-to-back schedule is 10 points
				score += checkBacktoBack(meetingList) * 10;
			}
			return checkConflicts ? IMPOSSIBLE : score;
		}

		private boolean checkConflicts(ArrayList<Meeting> meetingList) {
			// Checks if there is any conflicting class schedule
			// Condition: Schedule 1 starts on or before Schedule 2 and Schedule
			// 1 Ends after Schedule 2 Starts
			// TODO: Use Calendar and include minutes
			for (int i = 0; i < meetingList.size(); i++) {
				// Go through all meetings and check any time conflicts
				for (int j = i + 1; j < meetingList.size() - 1; j++) {
					// Compare each meeting to all other meetings
					if (meetingList.get(i).getDays() == meetingList.get(j).getDays()) {
						// Class on same days
						Time compHourOneStart = meetingList.get(i).getStartTime();
						Time compHourOneEnd = meetingList.get(i).getStopTime();
						Time compHourTwoStart = meetingList.get(j).getStartTime();
						if (compHourOneStart.compareTo(compHourTwoStart) <= 0
								&& compHourOneEnd.compareTo(compHourTwoStart) > 1) {
							// If Two Schedules have the same start time or
							// If One Schedule starts before the another and
							// ends after the another schedule's
							// start time, schedule conflicts
							// compHourOneEnd.compareTo(compHourTwoStart) == 0
							// would mean that there is a back-to-back schedule
							return false;
						}
					}
				}
			}
			return true;
		}

		private int checkBacktoBack(ArrayList<Meeting> meetingList) {
			// Checks if there is any back to back schedule and how many
			// PRECONDITION : The schedule does not conflict
			int backCount = 0;
			for (int i = 0; i < meetingList.size(); i++) {
				// Go through all meetings and check any time conflicts
				for (int j = i + 1; j < meetingList.size() - 1; j++) {
					// Compare each meeting to all other meetings
					if (Arrays.equals(meetingList.get(i).getDays(), meetingList.get(j).getDays())) {
						// Class on same days
						Time compHourOneEnd = meetingList.get(i).getStopTime();
						Time compHourTwoStart = meetingList.get(j).getStartTime();
						if (compHourOneEnd.compareTo(compHourTwoStart) == 0) {
							// Back to back schedule
							backCount++;
						}
					}
				}
			}
			return backCount;
		}
	}
}
