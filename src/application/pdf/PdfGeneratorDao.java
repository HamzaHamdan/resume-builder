package application.pdf;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.db.ConnectionFactory;
import application.model.Education;
import application.model.Experience;
import application.model.PersonalInfo;
import application.model.Skill;
import application.model.Summary;
import javafx.scene.control.Button;

public class PdfGeneratorDao {
	
	public PersonalInfo getPersonalInfoRecord() {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		PersonalInfo info = null;
		try {
			connection = ConnectionFactory.getConnection();
			String query = "select first_name, last_name, email_address, phone_number, physical_address, image_data from personalinfo";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				info = new PersonalInfo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
			}
		}catch(SQLException e) {
			System.out.println("Error occured while retrieving perosonal info details");
		}
		return info;
	}
	
	public byte[] getPictureBytesRecord() {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		byte[] imgData = null;
		try {
			connection = ConnectionFactory.getConnection();
			String query = "select image_data from personalinfo";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				imgData = rs.getBytes(1);
			}
		}catch(SQLException e) {
			System.out.println("Error occured while retrieving user's picture");
		}
		return imgData;
	}
	
	public ArrayList<Education> getEducationRecords() {
		
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Education> educationList = new ArrayList<Education>();
		try {
			connection = ConnectionFactory.getConnection();
			String query = "select id, school_name, from_Date, to_date, degree_type, degree_name, description from education";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Education education = new Education(rs.getLong(1), rs.getString(2), rs.getDate(3), rs.getDate(4),
						rs.getString(5), rs.getString(6), rs.getString(7), new Button(), new Button());
				educationList.add(education);
			}
		}catch(SQLException e) {
			System.out.println("Error occured while retrieving education details");
		}
		return educationList;
		
	}
	
	public ArrayList<Experience> getExperienceRecords() {
		
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Experience> experienceList = new ArrayList<Experience>();
		try {
			connection = ConnectionFactory.getConnection();
			String query = "select id, company_name, from_Date, to_date, position, description from experience";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Experience experience = new Experience(rs.getLong(1), rs.getString(2), rs.getDate(3), rs.getDate(4),
						rs.getString(5), rs.getString(6), new Button(), new Button());
				experienceList.add(experience);
			}
		}catch(SQLException e) {
			System.out.println("Error occured while retrieving experience details");
		}
		return experienceList;
	}
	
	public ArrayList<Skill> getSkillsRecords() {
		
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Skill> skillsList = new ArrayList<Skill>();
		try {
			connection = ConnectionFactory.getConnection();
			String query = "select id, skill from skills";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Skill skill = new Skill(rs.getLong(1), rs.getString(2), new Button(), new Button());
				skillsList.add(skill);
			}
		}catch(SQLException e) {
			System.out.println("Error occured while retrieving skills details");
		}
		return skillsList;
		
	}
	
	public Summary getSummaryRecord() {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		Summary summary = null;
		try {
			connection = ConnectionFactory.getConnection();
			// retrieve summary details
			String query = "select summary from summary";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			summary = new Summary();
			while (rs.next()) {
				summary.setSummary(rs.getString(1));
			}
			
		}catch(SQLException e) {
			System.out.println("Error occured while retrieving summary details");
		}
		
		return summary;
	}
	
	

}
