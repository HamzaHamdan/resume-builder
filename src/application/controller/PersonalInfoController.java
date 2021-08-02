package application.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;

import application.db.ConnectionFactory;
import application.extras.EnhancedAlert;
import application.model.PersonalInfo;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PersonalInfoController extends EnhancedAlert{

	@FXML
	private Button savePersonalInfoButton, cancelPersonalInfoButton;

	@FXML
	private ImageView avatarImageViewer;

	@FXML
	private TextField firstNameField, lastNameField, emailAddressField, physicalAddressField, phoneNumberField;

	@FXML
	private AnchorPane mainContainer;

	@FXML
	private GridPane personalInfoGrid;

	@FXML
	public void initialize() {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "select first_name, last_name, email_address, phone_number, physical_address, image_data from personalinfo";

		try {
			connection = ConnectionFactory.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				PersonalInfo info = new PersonalInfo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));

				firstNameField.setText(info.getFirstName());
				lastNameField.setText(info.getLastName());
				emailAddressField.setText(info.getEmailAddress());
				physicalAddressField.setText(info.getPhysicalAddress());
				phoneNumberField.setText(info.getPhoneNumber());

				Image img = new Image(new ByteArrayInputStream(rs.getBytes(6)));
				avatarImageViewer.setImage(img);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					/* Ignored */}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					/* Ignored */}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					/* Ignored */}
			}
		}

		avatarImageViewer.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				try {
					FileChooser fileChooser = new FileChooser();
					fileChooser.setTitle("Open Image File");
					File selectedFile = fileChooser.showOpenDialog((Stage) avatarImageViewer.getScene().getWindow());
					Image image = new Image(new FileInputStream(selectedFile));
					avatarImageViewer.setImage(image);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				event.consume();
			}
		});
	}

	public void personalInfoButtonHandler(ActionEvent event) {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("../view/PersonalInfo.fxml"));

			Connection connection = null;
			String query = "select first_name, last_name, email_address, phone_number, physical_address, image_data from personalinfo";
			Statement stmt = null;
			ResultSet rs = null;
			try {
				connection = ConnectionFactory.getConnection();
				stmt = connection.createStatement();
				rs = stmt.executeQuery(query);

				while (rs.next()) {
					PersonalInfo info = new PersonalInfo(rs.getString(1), rs.getString(2), rs.getString(3),
							rs.getString(4), rs.getString(5));

					TextField firstName = (TextField) root.lookup("#firstNameField");
					firstName.setText(info.getFirstName());

					TextField lastName = (TextField) root.lookup("#lastNameField");
					lastName.setText(info.getLastName());

					TextField emailAddress = (TextField) root.lookup("#emailAddressField");
					emailAddress.setText(info.getEmailAddress());

					TextField physicalAddress = (TextField) root.lookup("#physicalAddressField");
					physicalAddress.setText(info.getPhysicalAddress());

					TextField phoneNumber = (TextField) root.lookup("#phoneNumberField");
					phoneNumber.setText(info.getPhoneNumber());

					Image img = new Image(new ByteArrayInputStream(rs.getBytes(6)));

					ImageView imageView = (ImageView) root.lookup("#avatarImageViewer");
					imageView.setImage(img);

					mainContainer.getChildren().setAll(root);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						/* Ignored */}
				}
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						/* Ignored */}
				}
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						/* Ignored */}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void savePersonalInfoHandler(ActionEvent event) throws IOException {

		PersonalInfo info = new PersonalInfo();
		info.setFirstName(firstNameField.getText());
		info.setLastName(lastNameField.getText());
		info.setPhysicalAddress(physicalAddressField.getText());
		info.setPhoneNumber(phoneNumberField.getText());
		info.setEmailAddress(emailAddressField.getText());

		BufferedImage bImage = SwingFXUtils.fromFXImage(avatarImageViewer.getImage(), null);
		ByteArrayOutputStream s = new ByteArrayOutputStream();
		ImageIO.write(bImage, "png", s);
		byte[] res = s.toByteArray();
		s.close();
		
		if(info.getFirstName().isBlank() || info.getLastName().isBlank() || info.getEmailAddress().isBlank()|| info.getPhoneNumber().isBlank()
				|| info.getPhysicalAddress().isBlank()) {
			showAlertWindow("Make sure to fill all fields!", "error",420, 132);
			return;
		}

		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			String query = "select first_name from personalinfo";
			connection = ConnectionFactory.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);

			if (!rs.next()) {
				String statement = "insert into PersonalInfo (first_Name, last_name, email_address, physical_address, phone_number, image_data) "
						+ "values (?,?,?,?,?,?)";
				try {
					pstmt = connection.prepareStatement(statement);
					pstmt.setString(1, info.getFirstName());
					pstmt.setString(2, info.getLastName());
					pstmt.setString(3, info.getEmailAddress());
					pstmt.setString(4, info.getPhysicalAddress());
					pstmt.setString(5, info.getPhoneNumber());
					pstmt.setBytes(6, res);
					pstmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				String statement = "update personalinfo set first_name = ?, last_name = ?, email_address = ?, physical_address = ?"
						+ ", phone_number = ?, image_data = ?";
				try {
					pstmt = connection.prepareStatement(statement);
					pstmt.setString(1, firstNameField.getText());
					pstmt.setString(2, lastNameField.getText());
					pstmt.setString(3, emailAddressField.getText());
					pstmt.setString(4, physicalAddressField.getText());
					pstmt.setString(5, phoneNumberField.getText());

					BufferedImage bImg = SwingFXUtils.fromFXImage(avatarImageViewer.getImage(), null);
					ByteArrayOutputStream bytesArrayStream = new ByteArrayOutputStream();
					ImageIO.write(bImg, "png", bytesArrayStream);
					byte[] imgBytes = s.toByteArray();
					s.close();

					pstmt.setBytes(6, imgBytes);

					pstmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			showAlertWindow("Personal Information was updated successfully!", "info",420, 132);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					/* Ignored */}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					/* Ignored */}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					/* Ignored */}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					/* Ignored */}
			}
		}

	}
	
	public void cancelPersonalInfoButtonHandler() {
		initialize();
	}

}
