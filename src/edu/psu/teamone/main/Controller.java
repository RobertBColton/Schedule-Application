package edu.psu.teamone.main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Controller implements Initializable {
	@FXML
	private TabPane tabPane;
	@FXML
	private WebView web;

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
		// Shows Website
		Stage stage = new Stage();
		stage.setTitle("WebSite");
		viewWebSite(stage);
		stage.showAndWait();
	}

	@FXML
	protected final void handleDocAction(ActionEvent event) {
		// Show Documentation here
		final Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Sample Document");
		alert.showAndWait();
	}

	public void viewWebSite(final Stage stage) {
		// Load Website
		String webLink = "http://google.com"; // Change this link later
		stage.setWidth(830);
		stage.setHeight(650);
		Scene scene = new Scene(new Group());
		final WebView browser = new WebView();
		final WebEngine webEngine = browser.getEngine();
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(browser);
		webEngine.load(webLink);
		scene.setRoot(scrollPane);
		stage.setScene(scene);
		stage.show();
	}

}
