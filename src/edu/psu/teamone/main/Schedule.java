package edu.psu.teamone.main;

import java.util.Map;

public class Schedule {
	private Map<Section, Meeting> sections;
	public Schedule(){
		
	}

	public void addSection(Section section, Meeting meeting) {
		sections.put(section, meeting);
	}

	public boolean removeSection(Section section) {
		// If sections is null, false. Else try to remove section from sections
		// and return result
		return sections.size() == 0 ? false : sections.values().remove(section);
	}

	public Map<Section, Meeting> getSections() {
		return sections;
	}
}
