package edu.psu.teamone.main;

import java.util.Map;

public class Schedule {
	private Map<Section, Meeting> sections;

	public void addSection(Section section, Meeting meeting) {
		sections.put(section, meeting);
	}

	public boolean removeSection(Section section) {
		if (sections.size() == 0) {
			return false;
		} else {
			return sections.values().remove(section); // return true if success
		}
	}

	public Map<Section, Meeting> getSections() {
		return sections;
	}
}
