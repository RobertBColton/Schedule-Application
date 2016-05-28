package edu.psu.teamone.main;

import java.util.Map;

public class Schedule {
	private Map<Section, Meeting> sections;

	public void addSection(Section section, Meeting meeting) {
		sections.put(section, meeting);
	}

	public Map<Section, Meeting> getSections() {
		return sections;
	}
}
