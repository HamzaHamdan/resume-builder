package application.extras;

import application.controller.AlertController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EnhancedAlert {

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
