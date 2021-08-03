package application.controller;

/*
 * This is the controller class for Alert.fxml view
 *
 * MyUTSA ID: gos049
 * Assignment: Resume Builder Project
 * Class: CS-3443-01T-Summer-2021-Application Programming
 * 
 * @author: Hamza Hamdan
 * 
 */

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AlertController {
	
	/**
	 * JavaFX Label object
	 */
	@FXML
	private Label alertText;
	
	/**
	 * JavaFX ImageView object
	 */
	@FXML
	private ImageView iconImageView;
	
	/**
	 * JavaFX Button object
	 */
	@FXML
	private Button okAlertButton, closeAlertButton;
	
	/**
	 * initData method initializes the components of the alert message
	 * @param text alert text message
	 * @param msgType error or info message
	 */
	public void initData(String text, String msgType) {
		alertText.setText(text);
		closeAlertButton.setLayoutX(okAlertButton.getScene().getHeight() - 39);
		closeAlertButton.setLayoutX(okAlertButton.getScene().getWidth() - 39);
		
		Image img = null;
		
		if(msgType.equalsIgnoreCase("info")) {
			img = new Image("file:../../resources/logo/info-icon.png");
		}else if(msgType.equalsIgnoreCase("error")) {
			img = new Image("file:../../resources/logo/error-icon.png");
		}
		
		iconImageView.setImage(img);
		
		okAlertButton.setLayoutX((okAlertButton.getScene().getWidth()/2)-(okAlertButton.getPrefWidth()/2));
	  }
	
	/**
	 * initialize method handles mouse click events
	 * for both ok and Close buttons
	 */
	@FXML
	public void initialize() {
		okAlertButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Stage stage = (Stage) okAlertButton.getScene().getWindow();
				stage.close();
				event.consume();
			}
		});

		closeAlertButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Stage stage = (Stage) closeAlertButton.getScene().getWindow();
				stage.close();
				event.consume();
			}
		});
	}
	
}
