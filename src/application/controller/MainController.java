package application.controller;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController {
	
	@FXML
	private Button minimizeButton, closeButton, personalInfoButton, educationButton, skillsButton;

	@FXML
	private AnchorPane mainContainer;
	 
	public void minimizeButtonHandler(ActionEvent event){
		Stage stage = (Stage) minimizeButton.getScene().getWindow();
		stage.setIconified(true);
	}
	
	public void closeButtonHandler(ActionEvent event){
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}
	
	public void personalInfoButtonHandler(ActionEvent event) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/PersonalInfo.fxml"));
			mainContainer.getChildren().setAll(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void educationButtonHandler(ActionEvent event) {
		try {
			
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/Education.fxml"));
	        mainContainer.getChildren().setAll(root);
	  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void templatesButtonHandler(ActionEvent event) {
		try {
			
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/Templates.fxml"));
	        mainContainer.getChildren().setAll(root);
	  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void experienceButtonHandler(ActionEvent event) {
		try {
			
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/Experience.fxml"));
	        mainContainer.getChildren().setAll(root);
	  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void summaryButtonHandler(ActionEvent event) {
		try {
			
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/Summary.fxml"));
	        mainContainer.getChildren().setAll(root);
	  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void skillsButtonHandler(ActionEvent event) {
		try {
			
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/Skills.fxml"));
	        mainContainer.getChildren().setAll(root);
	  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
