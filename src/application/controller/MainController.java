package application.controller;

/*
 * This is the controller class for Main.fxml view
 * it mainly handles menu buttons and loads other fxml files
 *
 * MyUTSA ID: gos049
 * Assignment: Resume Builder Project
 * Class: CS-3443-01T-Summer-2021-Application Programming
 * 
 * @author: Hamza Hamdan
 * 
 */

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController {
	
	/**
	 * JavaFX Button object
	 */
	@FXML
	private Button minimizeButton, closeButton, personalInfoButton, educationButton, skillsButton;

	/**
	 * JavaFX AnchorPane object
	 */
	@FXML
	private AnchorPane mainContainer;
	
	/**
	 * minimizeButtonHandler method to minimize the screen
	 * @param event minimize button event
	 */
	public void minimizeButtonHandler(ActionEvent event){
		Stage stage = (Stage) minimizeButton.getScene().getWindow();
		stage.setIconified(true);
	}
	
	/**
	 * closeButtonHandler method to close the screen
	 * @param event close button event
	 */
	public void closeButtonHandler(ActionEvent event){
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}
	
	/**
	 * personalInfoButtonHandler method loads PersonalInfo.fxml view
	 * @param event close button event
	 */
	public void personalInfoButtonHandler(ActionEvent event) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/PersonalInfo.fxml"));
			mainContainer.getChildren().setAll(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * educationButtonHandler method loads Education.fxml view
	 * @param event close button event
	 */
	public void educationButtonHandler(ActionEvent event) {
		try {
			
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/Education.fxml"));
	        mainContainer.getChildren().setAll(root);
	  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * templatesButtonHandler method loads Templates.fxml view
	 * @param event close button event
	 */
	public void templatesButtonHandler(ActionEvent event) {
		try {
			
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/Templates.fxml"));
	        mainContainer.getChildren().setAll(root);
	  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * experienceButtonHandler method loads Experience.fxml view
	 * @param event close button event
	 */
	public void experienceButtonHandler(ActionEvent event) {
		try {
			
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/Experience.fxml"));
	        mainContainer.getChildren().setAll(root);
	  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * summaryButtonHandler method loads Summary.fxml view
	 * @param event close button event
	 */
	public void summaryButtonHandler(ActionEvent event) {
		try {
			
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/Summary.fxml"));
	        mainContainer.getChildren().setAll(root);
	  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * skillsButtonHandler method loads Skills.fxml view
	 * @param event close button event
	 */
	public void skillsButtonHandler(ActionEvent event) {
		try {
			
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../view/Skills.fxml"));
	        mainContainer.getChildren().setAll(root);
	  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
