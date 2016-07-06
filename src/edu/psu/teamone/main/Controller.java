package edu.psu.teamone.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Controller implements Initializable {
	@FXML
	private TabPane tabPane;
	@FXML
	private StackPane root;
	@FXML
	private TextField sectionName, sectionAbb, sectionId, startTime, endTime;
	// Class and Meeting
	@FXML
	private CheckBox dayMon, dayTues, dayWed, dayThur, dayFri;// MTWRF

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
				|| sectionId.getText().trim().isEmpty() || this.startTime.getText().trim().isEmpty()
				|| this.endTime.getText().trim().isEmpty()) {
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
		if (this.startTime.getText().matches(".*[a-zA-z]+.*") || this.endTime.getText().matches(".*[a-zA-z]+.*")
				|| sectionId.getText().matches(".*[a-zA-z]+.*")) {
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
			int courseId = Integer.parseInt(sectionId.getText());
			// addMeeting
			int meetingId = courseId;
			String startTime = this.startTime.getText() + ":00";
			String endTime = this.endTime.getText() + ":00";
			String days = (dayMon.isSelected() ? "1" : "0") + (dayTues.isSelected() ? "1" : "0")
					+ (dayWed.isSelected() ? "1" : "0") + (dayThur.isSelected() ? "1" : "0")
					+ (dayFri.isSelected() ? "1" : "0");
			db.addClass(courseId, courseName, courseAbb);
			db.addMeeting(meetingId, startTime, endTime, days);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Success");
			alert.setContentText("Successfully Added a new course");
			alert.showAndWait();
		}
	}
}
