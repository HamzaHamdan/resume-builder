package application.extras;

/*
 * This class loads the alert a long with the received message into
 * a new scene and then loads the scene in a new window
 *
 * MyUTSA ID: gos049
 * Assignment: Resume Builder Project
 * Class: CS-3443-01T-Summer-2021-Application Programming
 * 
 * @author: Hamza Hamdan
 * 
 */

import application.controller.AlertController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EnhancedAlert {

	/** 
	* showAlertWindow method loads Alert view into
	* a new scene and a new window 
	* @param str message to be displayed in the alert
	* @param msgType error or info message
	* @param width of the alert box
	* @param height of the alert box
	* 
	*/
	public void showAlertWindow(String str, String msgType, int width, int height) {

		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Alert.fxml"));
			
			AnchorPane root = (AnchorPane) loader.load();
			
			Scene alertScene = new Scene(root, width, height);

			Stage alertWindow = new Stage();
			alertWindow.initStyle(StageStyle.UNDECORATED);
			alertWindow.setTitle("Second Stage");
			alertWindow.setScene(alertScene);
			
			AlertController controller = loader.getController();
			controller.initData(str, msgType);
			
			alertWindow.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
