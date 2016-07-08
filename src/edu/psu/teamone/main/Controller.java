package edu.psu.teamone.main;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class Controller implements Initializable {
	@FXML
	private TabPane tabPane;
	@FXML
	private StackPane root;
	@FXML
	private TextField sectionName, sectionAbb, startTime, endTime;
	// Class and Meeting
	@FXML
	private CheckBox dayMon, dayTues, dayWed, dayThur, dayFri;// MTWRF
	@FXML
	private CheckBox editDayMon, editDayTues, editDayWed, editDayThur, editDayFri;// MTWRF
																					// on
																					// EditInformation
																					// Tab
	@FXML
	private TextField instructorName, instructorDiscipline, instructorId;
	@FXML
	private TextField editSectionId, editSectionName, editSectionAbb, editSectionStartTime, editSectionEndTime;
	@FXML
	private TableView sectionsTable;
	@FXML
	private TableColumn id, name, abbreviation, Time, Days;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	protected final void handleExitAction(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	protected final void handleAboutAction(ActionEvent event) {
		// Shows About
		final Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About Schedule Application");
		alert.setHeaderText("Copyright © 2016");
		alert.setContentText("By CMPSC 221 Team One");
		alert.showAndWait();
	}

	@FXML
	protected final void handleWebAction(ActionEvent event) {
		ScheduleApplication.getStaticHostServices().showDocument("https://github.com/CMPSC221/Schedule-Application");
	}

	@FXML
	protected final void handleDocAction(ActionEvent event) {
		ScheduleApplication.getStaticHostServices().showDocument("https://github.com/CMPSC221/Schedule-Application");
	}

	@FXML
	protected final void deleteSectionAction(ActionEvent event) {
		Database db = new Database();
		int sectionId = Integer.parseInt(editSectionId.getText().trim());
		db.deleteSection(sectionId);
		resetArea();
	}

	@FXML
	protected final void editSectionAction(ActionEvent event) {
		System.out.println("test");
		// resetArea();
	}

	@FXML
	protected final void loadSectionAction(ActionEvent event) {
		editDayMon.setSelected(false);
		editDayTues.setSelected(false);
		editDayWed.setSelected(false);
		editDayThur.setSelected(false);
		editDayFri.setSelected(false);
		Database db = new Database();
		ArrayList<String> sectionData = new ArrayList<String>();
		String sectionName = "", sectionAbb = "", sectionStartTime = "", sectionEndTime = "", days = "";
		if (editSectionId.getText().trim().equals("") || editSectionId.getText().trim().matches(".*[a-zA-z]+.*")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Error");
			alert.setHeaderText("Invalid Input");
			alert.setContentText("Cannot use numbers for sectionId");
			alert.showAndWait();
		} else {
			sectionData = db.getRowData(Integer.parseInt(editSectionId.getText().trim()));
		}
		sectionName = sectionData.get(0);// Course Name
		sectionAbb = sectionData.get(1); // Course Abb
		sectionStartTime = sectionData.get(2); // Course StartTime
		sectionEndTime = sectionData.get(3); // Course EndTime
		days = sectionData.get(4); // Course days
		editSectionName.setText(sectionName);
		editSectionAbb.setText(sectionAbb);
		editSectionStartTime.setText(sectionStartTime);
		editSectionEndTime.setText(sectionEndTime);
		if (days.charAt(0) == '1') {
			editDayMon.setSelected(true);
		}
		if (days.charAt(1) == '1') {
			editDayTues.setSelected(true);
		}
		if (days.charAt(2) == '1') {
			editDayWed.setSelected(true);
		}
		if (days.charAt(3) == '1') {
			editDayThur.setSelected(true);
		}
		if (days.charAt(4) == '1') {
			editDayFri.setSelected(true);
		}
	}

	protected void resetArea() {
		editDayMon.setSelected(false);
		editDayTues.setSelected(false);
		editDayWed.setSelected(false);
		editDayThur.setSelected(false);
		editDayFri.setSelected(false);

		dayMon.setSelected(false);
		dayTues.setSelected(false);
		dayWed.setSelected(false);
		dayThur.setSelected(false);
		dayFri.setSelected(false);

		editSectionId.setText("");
		editSectionName.setText("");
		editSectionAbb.setText("");
		editSectionStartTime.setText("");
		editSectionEndTime.setText("");

		sectionName.setText("");
		sectionAbb.setText("");
		startTime.setText("");
		endTime.setText("");
	}

	@FXML
	protected final void loadSections(ActionEvent event) {
		// Load Data Button Action
		// Loads all sections and meetings from db to arraylist
		Database db = new Database();
		ArrayList<Section> sections = db.getDataFromDBSection();
		ArrayList<Meeting> meetings = db.getDataFromDBMeeting();
		//db.getDataFromDB(sections, meetings);
		System.out.println(sections.size());
		System.out.println(meetings.size());
	}

	@FXML
	protected final void addSectionAction(ActionEvent event) throws Exception {
		boolean checkEmpty = false;
		if (!dayMon.isSelected() && !dayTues.isSelected() && !dayWed.isSelected() && !dayThur.isSelected()
				&& !dayFri.isSelected()) {
			checkEmpty = true;
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Error");
			alert.setHeaderText("Empty Box");
			alert.setContentText("Make sure you check at least one date box!");
			alert.showAndWait();
		}
		if (sectionName.getText().trim().isEmpty() || sectionAbb.getText().trim().isEmpty()
				|| this.startTime.getText().trim().isEmpty() || this.endTime.getText().trim().isEmpty()) {
			// || sectionId.getText().trim().isEmpty()
			checkEmpty = true;
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Error");
			alert.setHeaderText("Empty Text Box");
			alert.setContentText("Check if you have entered every text box");
			alert.showAndWait();
		}
		if (sectionName.getText().matches(".*\\d+.*")) {
			checkEmpty = true;
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Error");
			alert.setHeaderText("Invalid Input");
			alert.setContentText("Cannot use numbers for Course Name");
			alert.showAndWait();
		}
		if (!sectionAbb.getText().matches(".*[a-zA-z]+.*")) {
			checkEmpty = true;
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Error");
			alert.setHeaderText("Invalid Input");
			alert.setContentText("Cannot use only numbers for Course Abbreviation");
			alert.showAndWait();
		}
		if (this.startTime.getText().matches(".*[a-zA-z]+.*") || this.endTime.getText().matches(".*[a-zA-z]+.*")) {
			// || sectionId.getText().matches(".*[a-zA-z]+.*")
			Alert alert = new Alert(AlertType.WARNING);
			checkEmpty = true;
			alert.setTitle("Error");
			alert.setHeaderText("Invalid Input");
			alert.setContentText("Check your Time Text Box or Number Text Box");
			alert.showAndWait();
		} else if (!checkEmpty) {
			Database db = new Database();
			// addClass inputs
			String courseName = sectionName.getText();
			String courseAbb = sectionAbb.getText();
			// int courseId = Integer.parseInt(sectionId.getText());
			// addMeeting
			// int meetingId = courseId;
			String startTime = this.startTime.getText() + ":00";
			String endTime = this.endTime.getText() + ":00";
			String days = (dayMon.isSelected() ? "1" : "0") + (dayTues.isSelected() ? "1" : "0")
					+ (dayWed.isSelected() ? "1" : "0") + (dayThur.isSelected() ? "1" : "0")
					+ (dayFri.isSelected() ? "1" : "0");
			int courseId = db.generateId();
			db.addClass(courseId, courseName, courseAbb);
			db.addMeeting(courseId, startTime, endTime, days);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Success");
			alert.setContentText("Successfully Added a new course");
			alert.showAndWait();
		}
		resetArea();
	}
}
