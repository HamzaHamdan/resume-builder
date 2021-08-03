package application.controller;

/*
 * This is the controller class for Skills.fxml
 *
 * MyUTSA ID: gos049
 * Assignment: Resume Builder Project
 * Class: CS-3443-01T-Summer-2021-Application Programming
 * 
 * @author: Hamza Hamdan
 * 
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.db.ConnectionFactory;
import application.extras.EnhancedAlert;
import application.model.Skill;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class SkillsController extends EnhancedAlert{
	
	/**
	 * constant to store number of items to display per page
	 */
	public static final int ITEMS_PER_PAGE = 4;
	
	/**
	 * JavaFX Pagination object
	 */
	@FXML
	private Pagination skillsPagination;
	
	/**
	 * JavaFX Label object
	 */
	@FXML
	private Label hiddenID;
	
	/**
	 * JavaFX TextArea object
	 */
	@FXML
	private TextArea skillDescField;
	
	/**
	 * JavaFX Button object
	 */
	@FXML
	private Button addSkillButton;
	
	/**
	 * JavaFX TableView object
	 */
	@FXML
	private TableView<Skill> skillsTableView;
	
	/**
	 * JavaFX TableColumn object
	 */
	@FXML
	private TableColumn<Skill, String> skillColumn;
	
	/**
	 * JavaFX TableColumn object
	 */
	@FXML
	private TableColumn<Skill, Button> deleteActionColumn;
	
	/**
	 * JavaFX TableColumn object
	 */
	@FXML
	private TableColumn<Skill, Button> editActionColumn;
	
	/**
	 * initialize method loads initial date for the skills view
	 */
	@FXML
	public void initialize() {
		
		try {
			Connection connection = ConnectionFactory.getConnection();
			String query = "select id, skill from skills";
			Statement stmt;
			stmt = connection.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
				ObservableList<Skill> skillsList = FXCollections.observableArrayList();
		        while(rs.next()){
		        	Skill skill = new Skill(rs.getLong(1), rs.getString(2), new Button(), new Button());
		            long id = rs.getLong(1);
		            skill.getDeleteButton().setOnAction((event) -> {handleDeleteButtonAction(id);});
		            skill.getDeleteButton().setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.REMOVE, "9px"));
		            skill.getEditButton().setOnAction((event) -> {handleEditButtonAction(id);});
		            skill.getEditButton().setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.EDIT, "9px"));
		            skillsList.add(skill);
		        }
		        
		        skillsPagination.setPageCount((int) Math.ceil((float)skillsList.size()/(float)ITEMS_PER_PAGE));
		        skillsPagination.setCurrentPageIndex(0);
		        skillsPagination.setMaxPageIndicatorCount(5);
		        
		        skillsPagination.setPageFactory(new Callback<Integer, Node>() {
		            public Node call(Integer pageIndex) {
		                return createPage(pageIndex, skillsList);
		            }
		        });
		        
				skillDescField.setText("");
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
		String query = "select id, skill from skills where id = "
				+ id;
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				skillDescField.setText(rs.getString(2));
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
			String statement = "delete from skills where id = ?";
			
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
	 * createPage method takes skills records as input
	 * and page number, then returns the list of skills
	 * records for the entered page
	 * @param pageIndex page number
	 * @param skillsList skills records
	 * @return skillsTableView tabulated skills records
	 */
	public TableView<Skill> createPage(int pageIndex, ObservableList<Skill> skillsList) {

		ObservableList<Skill> subList = null;
		if(!skillsList.isEmpty()) {
			if(pageIndex == skillsPagination.getPageCount()-1) {
				subList = FXCollections.observableArrayList(skillsList.subList(pageIndex*ITEMS_PER_PAGE, skillsList.size()));
			}else if (pageIndex < skillsPagination.getPageCount()-1) {
				subList = FXCollections.observableArrayList(skillsList.subList(pageIndex*ITEMS_PER_PAGE, (ITEMS_PER_PAGE*pageIndex)+ITEMS_PER_PAGE));
			}
		}
		skillsTableView.setItems(subList);
		
		skillColumn.setCellValueFactory(new PropertyValueFactory<Skill, String>("skillDesc"));
		skillColumn.setPrefWidth(500);
		 
        deleteActionColumn.setCellValueFactory(new PropertyValueFactory<Skill, Button>("deleteButton"));
        deleteActionColumn.setPrefWidth(50);
        
        editActionColumn.setCellValueFactory(new PropertyValueFactory<Skill, Button>("editButton"));
        editActionColumn.setPrefWidth(50);
        
        return skillsTableView;
    }
	
	/**
	 * addSkillButtonHandler method saves changes to 
	 * already existing records or creates new ones
	 * @param event button events object
	 */
	public void addSkillButtonHandler(ActionEvent event) {
		Connection connection = ConnectionFactory.getConnection();
		
		if(skillDescField.getText().isBlank()) {
			showAlertWindow("Make sure to fill all fields!", "error",420, 132);
			return;
		}
		
		if (hiddenID.getText().equalsIgnoreCase("")) {
			if (connection != null) {
				String statement = "insert into skills (skill) "
						+ "values (?)";

				PreparedStatement pstmt;
				try {
					pstmt = connection.prepareStatement(statement);
					pstmt.setString(1, skillDescField.getText());
					pstmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			if (connection != null) {
				String statement = "update skills set skill = ? where id = ?";

				PreparedStatement pstmt;
				try {
					pstmt = connection.prepareStatement(statement);
					pstmt.setString(1, skillDescField.getText());
					pstmt.setString(2, hiddenID.getText());
					pstmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		initialize();

	}
	
}
