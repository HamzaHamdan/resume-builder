package application.controller;

/*
 * This is the controller class for Experience.fxml view
 *
 * MyUTSA ID: gos049
 * Assignment: Resume Builder Project
 * Class: CS-3443-01T-Summer-2021-Application Programming
 * 
 * @author: Hamza Hamdan
 * 
 */

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import application.db.ConnectionFactory;
import application.extras.EnhancedAlert;
import application.model.Experience;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class ExperienceController extends EnhancedAlert{
	
	/**
	 * constant to store number of items to display per page
	 */
	public static final int ITEMS_PER_PAGE = 4;
	
	/**
	 * JavaFX Pagination object
	 */
	@FXML
	private Pagination experiencePagination;
	
	/**
	 * JavaFX TextField object
	 */
	@FXML
	private TextField companyNameField, positionField;
	
	/**
	 * JavaFX Label object
	 */
	@FXML
	private Label hiddenID;
	
	/**
	 * JavaFX DatePicker object
	 */
	@FXML
	private DatePicker experienceFromDateField, experienceToDateField;
	
	/**
	 * JavaFX TextArea object
	 */
	@FXML
	private TextArea experienceDescField;
	
	/**
	 * JavaFX Button object
	 */
	@FXML
	private Button addExperienceButton;
	
	/**
	 * JavaFX TableView object
	 */
	@FXML
	private TableView<Experience> experienceTableView;
	
	/**
	 * JavaFX TableColumn object
	 */
	@FXML
	private TableColumn<Experience, String> companyNameColumn;
	
	/**
	 * JavaFX TableColumn object
	 */
	@FXML
	private TableColumn<Experience, LocalDate> experienceFromDateColumn;
	
	/**
	 * JavaFX TableColumn object
	 */
	@FXML
	private TableColumn<Experience, LocalDate> experienceToDateColumn;
	
	/**
	 * JavaFX TableColumn object
	 */
	@FXML
	private TableColumn<Experience, String> positionColumn;
	
	/**
	 * JavaFX TableColumn object
	 */
	@FXML
	private TableColumn<Experience, String> descColumn;
	
	/**
	 * JavaFX TableColumn object
	 */
	@FXML
	private TableColumn<Experience, Button> deleteActionColumn;
	
	/**
	 * JavaFX TableColumn object
	 */
	@FXML
	private TableColumn<Experience, Button> editActionColumn;
	
	/**
	 * initialize method loads initial date for the experience view
	 */
	@FXML
	public void initialize() {
		
		try {
			Connection connection = ConnectionFactory.getConnection();
			String query = "select id, company_name, from_Date, to_date, position, description from experience";
			Statement stmt;
			stmt = connection.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
				ObservableList<Experience> experienceList = FXCollections.observableArrayList();
		        while(rs.next()){
		            Experience experience = new Experience(rs.getLong(1), rs.getString(2), rs.getDate(3), rs.getDate(4),
				            rs.getString(5), rs.getString(6), new Button(), new Button());
		            long id = rs.getLong(1);
		            experience.getDeleteButton().setOnAction((event) -> {handleDeleteButtonAction(id);});
		            experience.getDeleteButton().setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.REMOVE, "9px"));
		            experience.getEditButton().setOnAction((event) -> {handleEditButtonAction(id);});
		            experience.getEditButton().setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.EDIT, "9px"));
		            experienceList.add(experience);
		        }
		        
		        experiencePagination.setPageCount((int) Math.ceil((float)experienceList.size()/(float)ITEMS_PER_PAGE));
		        experiencePagination.setCurrentPageIndex(0);
		        experiencePagination.setMaxPageIndicatorCount(5);
		        
		        experiencePagination.setPageFactory(new Callback<Integer, Node>() {
		            public Node call(Integer pageIndex) {
		                return createPage(pageIndex, experienceList);
		            }
		        });
		        
				positionField.setText("");
				experienceDescField.setText("");
				experienceFromDateField.setValue(null);
				experienceToDateField.setValue(null);
				companyNameField.setText("");
				hiddenID.setText("");
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
       
	}
	
	/**
	 * handleEditButtonAction handles edit button event
	 * @param id record id to be edited
	 */
	private void handleEditButtonAction(long id) {
		Connection connection = ConnectionFactory.getConnection();
		String query = "select id, company_name, from_Date, to_date, position, description from experience where id = "
				+ id;
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				positionField.setText(rs.getString(5));
				experienceFromDateField.setValue(rs.getDate(3).toLocalDate());
				experienceToDateField.setValue(rs.getDate(4).toLocalDate());
				experienceDescField.setText(rs.getString(6));
				companyNameField.setText(rs.getString(2));
				hiddenID.setText(String.valueOf(rs.getLong(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * handleDeleteButtonAction handles delete button event
	 * @param id record id to be deleted
	 */
	private void handleDeleteButtonAction(long id) {
		Connection connection = ConnectionFactory.getConnection();
		
		if (connection != null) {
			String statement = "delete from experience where id = ?";
			
			PreparedStatement pstmt;
			try {
				pstmt = connection.prepareStatement(statement);
				pstmt.setLong(1, id);
		        pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	        
		}
		
		initialize();

	}

	/**
	 * createPage method takes experience records as input
	 * and page number, then returns the list of experience
	 * records for the entered page
	 * @param pageIndex page number
	 * @param experienceList experience history records
	 * @return experienceTableView tabulated experience history records
	 */
	public TableView<Experience> createPage(int pageIndex, ObservableList<Experience> experienceList) {
		
		ObservableList<Experience> subList = null;
		if(!experienceList.isEmpty()) {
			if(pageIndex == experiencePagination.getPageCount()-1) {
				subList = FXCollections.observableArrayList(experienceList.subList(pageIndex*ITEMS_PER_PAGE, experienceList.size()));
			}else if (pageIndex < experiencePagination.getPageCount()-1) {
				subList = FXCollections.observableArrayList(experienceList.subList(pageIndex*ITEMS_PER_PAGE, (ITEMS_PER_PAGE*pageIndex)+ITEMS_PER_PAGE));
			}
		}
		
		experienceTableView.setItems(subList);
		
		companyNameColumn.setCellValueFactory(new PropertyValueFactory<Experience, String>("comapnyName"));
        
        experienceFromDateColumn.setCellValueFactory(new PropertyValueFactory<Experience, LocalDate>("fromDate"));
        
        experienceToDateColumn.setCellValueFactory(new PropertyValueFactory<Experience, LocalDate>("toDate"));
        
        positionColumn.setCellValueFactory(new PropertyValueFactory<Experience, String>("position"));
        
        descColumn.setCellValueFactory(new PropertyValueFactory<Experience, String>("description"));
        
        deleteActionColumn.setCellValueFactory(new PropertyValueFactory<Experience, Button>("deleteButton"));
        deleteActionColumn.setPrefWidth(50);
        
        editActionColumn.setCellValueFactory(new PropertyValueFactory<Experience, Button>("editButton"));
        editActionColumn.setPrefWidth(50);
        
        return experienceTableView;
    }
	
	/**
	 * addExperienceButtonHandler method saves changes to 
	 * already existing records or creates new ones
	 * @param event button events object
	 */
	public void addExperienceButtonHandler(ActionEvent event) {
		Connection connection = ConnectionFactory.getConnection();
		
		if(companyNameField.getText().isBlank() || experienceFromDateField.getValue() == null || 
				experienceToDateField.getValue() == null|| positionField.getText().isBlank()
				|| experienceDescField.getText().isBlank()) {
			showAlertWindow("Make sure to fill all fields!", "error",420, 132);
			return;
		}
		
		if (hiddenID.getText().equalsIgnoreCase("")) {
			if (connection != null) {
				String statement = "insert into experience (company_name, from_Date, to_date, position, description) "
						+ "values (?,?,?,?,?)";

				PreparedStatement pstmt;
				try {
					pstmt = connection.prepareStatement(statement);
					pstmt.setString(1, companyNameField.getText());
					pstmt.setDate(2, Date.valueOf(experienceFromDateField.getValue()));
					pstmt.setDate(3, Date.valueOf(experienceToDateField.getValue()));
					pstmt.setString(4, positionField.getText());
					pstmt.setString(5, experienceDescField.getText());
					pstmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			if (connection != null) {
				String statement = "update experience set company_name = ?, from_Date = ?, to_date = ?, "
						+ "position = ?, description = ? where id = ?";

				PreparedStatement pstmt;
				try {
					pstmt = connection.prepareStatement(statement);
					pstmt.setString(1, companyNameField.getText());
					pstmt.setDate(2, Date.valueOf(experienceFromDateField.getValue()));
					pstmt.setDate(3, Date.valueOf(experienceToDateField.getValue()));
					pstmt.setString(4, positionField.getText());
					pstmt.setString(5, experienceDescField.getText());
					pstmt.setString(6, hiddenID.getText());
					pstmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		initialize();

	}
	
}
