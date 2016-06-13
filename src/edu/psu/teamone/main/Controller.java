package edu.psu.teamone.main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;

public class Controller implements Initializable {
	@FXML
	private TabPane tabPane;
	@FXML
	private StackPane root;

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
		ScheduleApplication.getStaticHostServices().showDocument(
			"https://github.com/CMPSC221/Schedule-Application");
	}

	@FXML
	protected final void handleDocAction(ActionEvent event) {
		ScheduleApplication.getStaticHostServices().showDocument(
			"https://github.com/CMPSC221/Schedule-Application");
	}
}
