package application.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.db.ConnectionFactory;
import application.extras.EnhancedAlert;
import application.model.Summary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class SummaryController extends EnhancedAlert{
	
	@FXML
	private TextArea summaryField;

	@FXML
	public void initialize() {

		Statement stmt = null;
		Connection connection = null;
		ResultSet rs = null;
		try {
			String query = "select summary from summary";
			connection = ConnectionFactory.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Summary summary = new Summary(rs.getString(1));
				summaryField.setText(summary.getSummary());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException e) { /* Ignored */}
		    }
		    if (stmt != null) {
		        try {
		        	stmt.close();
		        } catch (SQLException e) { /* Ignored */}
		    }
		    if (connection != null) {
		        try {
		        	connection.close();
		        } catch (SQLException e) { /* Ignored */}
		    }
		}

	}

	public void saveSummaryButtonHandler(ActionEvent event) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
			try {
				connection = ConnectionFactory.getConnection();
				String query = "select summary from summary";
				stmt = connection.createStatement();
				rs = stmt.executeQuery(query);
				if(!rs.next()) {
					String insertStatement = "insert into summary (summary) " + "values (?)";
					pstmt = connection.prepareStatement(insertStatement);
					pstmt.setString(1, summaryField.getText());
					pstmt.executeUpdate();
				}else {
					String updateStatement = "update summary set summary = ?";
					pstmt = connection.prepareStatement(updateStatement);
					pstmt.setString(1, summaryField.getText());
					pstmt.executeUpdate();
				}
				
				showAlertWindow("Summary was updated successfully!", "info", 380, 132);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if (rs != null) {
			        try {
			            rs.close();
			        } catch (SQLException e) { /* Ignored */}
			    }
			    if (stmt != null) {
			        try {
			        	stmt.close();
			        } catch (SQLException e) { /* Ignored */}
			    }
			    if (pstmt != null) {
			        try {
			        	pstmt.close();
			        } catch (SQLException e) { /* Ignored */}
			    }
			    if (connection != null) {
			        try {
			        	connection.close();
			        } catch (SQLException e) { /* Ignored */}
			    }
			}
	}
	
	public void cancelSummaryButtonHandler() {
		initialize();
	}

}