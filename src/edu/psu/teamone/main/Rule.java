package edu.psu.teamone.main;

import java.util.ArrayList;
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
				// 1 back-to-back schedule is 10 points
				score += checkBacktoBack(meetingList) * 10;
			}
			return checkConflicts ? IMPOSSIBLE : score;
		}

		@SuppressWarnings("deprecation") // TODO: Use Calendar Class
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
						int compHourOneStart = meetingList.get(i).getStartTime().getHours();
						int compHourOneEnd = meetingList.get(i).getStopTime().getHours();
						int compHourTwoStart = meetingList.get(j).getStartTime().getHours();
						if (compHourOneStart <= compHourTwoStart && compHourOneEnd > compHourTwoStart) {
							// If one class starts before other class starts and
							// ends after that class starts
							// It means that the schedule conflicts.
							return false;
						}
					}
				}
			}
			return true;
		}

		@SuppressWarnings("deprecation")
		private int checkBacktoBack(ArrayList<Meeting> meetingList) {
			// Checks if there is any back to back schedule and how many
			// PRECONDITION : The schedule does not conflict
			int backCount = 0;
			for (int i = 0; i < meetingList.size(); i++) {
				// Go through all meetings and check any time conflicts
				for (int j = i + 1; j < meetingList.size() - 1; j++) {
					// Compare each meeting to all other meetings
					if (meetingList.get(i).getDays() == meetingList.get(j).getDays()) {
						// Class on same days
						int compHourOneEnd = meetingList.get(i).getStopTime().getHours();
						int compHourTwoStart = meetingList.get(j).getStartTime().getHours();
						// Back to back schedule
						if (compHourOneEnd == compHourTwoStart) {
							backCount++;
						}
					}
				}
			}
			return backCount;
		}
	}
}
