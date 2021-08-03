package application.controller;

/*
 * This is the controller class for Education.fxml view
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
import application.model.Education;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class EducationController extends EnhancedAlert{
	
	/**
	 * Degree Type combo box initial data stored in an observable list object
	 */
	ObservableList<String> degreeTypeList = FXCollections.observableArrayList("Bachelor's Degree", "Master's Degree", "Training");
	
	/**
	 * constant to store number of items to display per page
	 */
	public static final int ITEMS_PER_PAGE = 4;
	
	/**
	 * JavaFX Pagination object
	 */
	@FXML
	private Pagination educationPagination;
	
	/**
	 * JavaFX ComboBox object
	 */
	@FXML
	private ComboBox<String> degreeTypeCombo;
	
	/**
	 * JavaFX TextField object
	 */
	@FXML
	private TextField schoolNameField, degreeNameField;
	
	/**
	 * JavaFX Label object
	 */
	@FXML
	private Label hiddenID;
	
	/**
	 * JavaFX DatePicker object
	 */
	@FXML
	private DatePicker educationFromDateField, educationToDateField;
	
	/**
	 * JavaFX TextArea object
	 */
	@FXML
	private TextArea educationDescField;
	
	/**
	 * JavaFX Button object
	 */
	@FXML
	private Button addEducationButton;
	
	/**
	 * JavaFX TableView object
	 */
	@FXML
	private TableView<Education> educationTableView;
	
	/**
	 * JavaFX TableColumn object
	 */
	@FXML
	private TableColumn<Education, String> schoolNameColumn;
	
	/**
	 * JavaFX TableColumn object
	 */
	@FXML
	private TableColumn<Education, LocalDate> degreeFromDateColumn;
	
	/**
	 * JavaFX TableColumn object
	 */
	@FXML
	private TableColumn<Education, LocalDate> degreeToDateColumn;
	
	/**
	 * JavaFX TableColumn object
	 */
	@FXML
	private TableColumn<Education, String> degreeTypeColumn;
	
	/**
	 * JavaFX TableColumn object
	 */
	@FXML
	private TableColumn<Education, String> degreeNameColumn;
	
	/**
	 * JavaFX TableColumn object
	 */
	@FXML
	private TableColumn<Education, String> descColumn;
	
	/**
	 * JavaFX TableColumn object
	 */
	@FXML
	private TableColumn<Education, Button> deleteActionColumn;
	
	/**
	 * JavaFX TableColumn object
	 */
	@FXML
	private TableColumn<Education, Button> editActionColumn;
	
	/**
	 * initialize method loads initial date for the education view
	 */
	@FXML
	public void initialize() {
		
		try {
			Connection connection = ConnectionFactory.getConnection();
			String query = "select id, school_name, from_Date, to_date, degree_type, degree_name, description from education";
			Statement stmt;
			stmt = connection.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
				ObservableList<Education> educationList = FXCollections.observableArrayList();
		        while(rs.next()){
		            Education education = new Education(rs.getLong(1), rs.getString(2), rs.getDate(3), rs.getDate(4),
				            rs.getString(5), rs.getString(6), rs.getString(7), new Button(), new Button());
		            long id = rs.getLong(1);
		            education.getDeleteButton().setOnAction((event) -> {handleDeleteButtonAction(id);});
		            education.getDeleteButton().setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.REMOVE, "9px"));
		            education.getEditButton().setOnAction((event) -> {handleEditButtonAction(id);});
		            education.getEditButton().setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.EDIT, "9px"));
		            educationList.add(education);
		        }
		        
		        educationPagination.setPageCount((int) Math.ceil((float)educationList.size()/(float)ITEMS_PER_PAGE));
		        educationPagination.setCurrentPageIndex(0);
		        educationPagination.setMaxPageIndicatorCount(5);
		        
		        educationPagination.setPageFactory(new Callback<Integer, Node>() {
		            public Node call(Integer pageIndex) {
		                return createPage(pageIndex, educationList);
		            }
		        });
		        
		        degreeTypeCombo.setValue("Bachelor's Degree");
				degreeTypeCombo.setItems(degreeTypeList);
				degreeNameField.setText("");
				educationDescField.setText("");
				educationFromDateField.setValue(null);
				educationToDateField.setValue(null);
				schoolNameField.setText("");
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
		String query = "select id, school_name, from_Date, to_date, degree_type, degree_name, description from education where id = "
				+ id;
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {

				degreeTypeCombo.setValue(rs.getString(5));
				degreeTypeCombo.setItems(degreeTypeList);
				degreeNameField.setText(rs.getString(6));
				educationFromDateField.setValue(rs.getDate(3).toLocalDate());
				educationToDateField.setValue(rs.getDate(4).toLocalDate());
				educationDescField.setText(rs.getString(7));
				schoolNameField.setText(rs.getString(2));
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
			String statement = "delete from education where id = ?";
			
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
	 * createPage method takes education records as input
	 * and page number, then returns the list of education
	 * records for the entered page
	 * @param pageIndex page number
	 * @param educationList education history records
	 * @return educationTableView tabulated education history records
	 */
	public TableView<Education> createPage(int pageIndex, ObservableList<Education> educationList) {
		
		ObservableList<Education> subList = null;
		if(!educationList.isEmpty()) {
			if(pageIndex == educationPagination.getPageCount()-1) {
				subList = FXCollections.observableArrayList(educationList.subList(pageIndex*ITEMS_PER_PAGE, educationList.size()));
			}else if (pageIndex < educationPagination.getPageCount()-1) {
				subList = FXCollections.observableArrayList(educationList.subList(pageIndex*ITEMS_PER_PAGE, (ITEMS_PER_PAGE*pageIndex)+ITEMS_PER_PAGE));
			}
		}
		
		educationTableView.setItems(subList);
		
        schoolNameColumn.setCellValueFactory(new PropertyValueFactory<Education, String>("schoolName"));
        
        degreeFromDateColumn.setCellValueFactory(new PropertyValueFactory<Education, LocalDate>("fromDate"));
        
        degreeToDateColumn.setCellValueFactory(new PropertyValueFactory<Education, LocalDate>("toDate"));
        
        degreeTypeColumn.setCellValueFactory(new PropertyValueFactory<Education, String>("degreeType"));
        
        degreeNameColumn.setCellValueFactory(new PropertyValueFactory<Education, String>("degreeName"));
        
        descColumn.setCellValueFactory(new PropertyValueFactory<Education, String>("description"));
        
        deleteActionColumn.setCellValueFactory(new PropertyValueFactory<Education, Button>("deleteButton"));
        deleteActionColumn.setPrefWidth(50);
        
        editActionColumn.setCellValueFactory(new PropertyValueFactory<Education, Button>("editButton"));
        editActionColumn.setPrefWidth(50);
        
        return educationTableView;
    }
	
	/**
	 * addEducationButtonHandler method saves changes to 
	 * already existing records or creates new ones
	 * @param event button events object
	 */
	public void addEducationButtonHandler(ActionEvent event) {
		Connection connection = ConnectionFactory.getConnection();
		
		if(schoolNameField.getText().isBlank() || educationFromDateField.getValue() == null || 
				educationToDateField.getValue() == null|| degreeNameField.getText().isBlank()
				|| educationDescField.getText().isBlank()) {
			showAlertWindow("Make sure to fill all fields!", "error",420, 132);
			return;
		}
		
		if (hiddenID.getText().equalsIgnoreCase("")) {
			if (connection != null) {
				String statement = "insert into education (school_name, from_Date, to_date, degree_type, degree_name, description) "
						+ "values (?,?,?,?,?,?)";

				PreparedStatement pstmt;
				try {
					pstmt = connection.prepareStatement(statement);
					pstmt.setString(1, schoolNameField.getText());
					pstmt.setDate(2, Date.valueOf(educationFromDateField.getValue()));
					pstmt.setDate(3, Date.valueOf(educationToDateField.getValue()));
					pstmt.setString(4, degreeTypeCombo.getValue());
					pstmt.setString(5, degreeNameField.getText());
					pstmt.setString(6, educationDescField.getText());
					pstmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			if (connection != null) {
				String statement = "update education set school_name = ?, from_Date = ?, to_date = ?, degree_type = ?"
						+ ", degree_name = ?, description = ? where id = ?";

				PreparedStatement pstmt;
				try {
					pstmt = connection.prepareStatement(statement);
					pstmt.setString(1, schoolNameField.getText());
					pstmt.setDate(2, Date.valueOf(educationFromDateField.getValue()));
					pstmt.setDate(3, Date.valueOf(educationToDateField.getValue()));
					pstmt.setString(4, degreeTypeCombo.getValue());
					pstmt.setString(5, degreeNameField.getText());
					pstmt.setString(6, educationDescField.getText());
					pstmt.setString(7, hiddenID.getText());
					pstmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		initialize();

	}
	
}
