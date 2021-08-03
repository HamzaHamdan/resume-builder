package application.controller;

/*
 * This is the controller class for PersonalInfo.fxml view
 *
 * MyUTSA ID: gos049
 * Assignment: Resume Builder Project
 * Class: CS-3443-01T-Summer-2021-Application Programming
 * 
 * @author: Hamza Hamdan
 * 
 */

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

	/**
	 * JavaFX Button object
	 */
	@FXML
	private Button savePersonalInfoButton, cancelPersonalInfoButton;

	/**
	 * JavaFX ImageView object
	 */
	@FXML
	private ImageView avatarImageViewer;

	/**
	 * JavaFX TextField object
	 */
	@FXML
	private TextField firstNameField, lastNameField, emailAddressField, physicalAddressField, phoneNumberField;

	/**
	 * JavaFX AnchorPane object
	 */
	@FXML
	private AnchorPane mainContainer;

	/**
	 * JavaFX GridPane object
	 */
	@FXML
	private GridPane personalInfoGrid;

	/**
	 * initialize method loads personal info and user image
	 * from the database when page is loaded
	 */
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
					
					if(selectedFile == null) {
						return;
					}
					
					Image image = new Image(new FileInputStream(selectedFile));
					avatarImageViewer.setImage(image);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				event.consume();
			}
		});
	}

	/**
	 * savePersonalInfoHandler method creates or updates personal info record
	 * @param event save button event object
	 */
	public void savePersonalInfoHandler(ActionEvent event) {

		PersonalInfo info = new PersonalInfo();
		info.setFirstName(firstNameField.getText());
		info.setLastName(lastNameField.getText());
		info.setPhysicalAddress(physicalAddressField.getText());
		info.setPhoneNumber(phoneNumberField.getText());
		info.setEmailAddress(emailAddressField.getText());

		byte[] imgBytes = null;
		try {
			BufferedImage bImg = SwingFXUtils.fromFXImage(avatarImageViewer.getImage(), null);
			ByteArrayOutputStream bytesArrayStream = new ByteArrayOutputStream();
			ImageIO.write(bImg, "png", bytesArrayStream);
			imgBytes = bytesArrayStream.toByteArray();
			bytesArrayStream.close();
		}catch(IOException exc) {
			exc.printStackTrace();
		}
		
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
					pstmt.setBytes(6, imgBytes);
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
	
	/**
	 * cancelPersonalInfoButtonHandler method reloads the personal info view
	 */
	public void cancelPersonalInfoButtonHandler() {
		initialize();
	}

}
