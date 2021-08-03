package application.model;

/*
 * Education Model class
 * 
 * MyUTSA ID: gos049
 * Assignment: Resume Builder Project
 * Class: CS-3443-01T-Summer-2021-Application Programming
 * 
 * @author: Hamza Hamdan
 * 
 */

import java.sql.Date;

import javafx.scene.control.Button;

public class Education {
	
	/** 
	* id of the education record
	*/
	private long id;
	
	/** 
	* name of the school in the education record
	*/
	private String schoolName;
	
	/** 
	* education record from date value
	*/
	private Date fromDate;
	
	/** 
	* education record to date value
	*/
	private Date toDate;
	
	/** 
	* education record degree type value
	*/
	private String degreeType;
	
	/** 
	* education record degree name value
	*/
	private String degreeName;
	
	/** 
	* education record description value
	*/
	private String description;
	
	/** 
	* education record delete button
	*/
	private Button deleteButton;
	
	/** 
	* education record edit button
	*/
	private Button editButton;
	
	/** 
	* Education class no-args constructor
	* 
	*/
	public Education() {
		super();
	}
	
	/** 
	* Education class constructor
	* @param id long
	* @param schoolName String
	* @param fromDate sql.Date
	* @param toDate sql.Date
	* @param degreeType String
	* @param degreeName String
	* @param description String
	* @param deleteButton Button
	* @param editButton Button
	*/
	public Education(long id, String schoolName, Date fromDate, Date toDate, String degreeType, String degreeName,
			String description, Button deleteButton, Button editButton) {
		super();
		this.id = id;
		this.schoolName = schoolName;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.degreeType = degreeType;
		this.degreeName = degreeName;
		this.description = description;
		this.deleteButton = new Button();
		this.editButton = new Button();
	}
	
	/** 
	* gets the record id
	* @return id
	*/
	public long getId() {
		return id;
	}
	
	/** 
	* sets the record id
	* @param id long
	*/
	public void setId(long id) {
		this.id = id;
	}
	
	/** 
	* gets the education object school name
	* @return name of the school
	*/
	public String getSchoolName() {
		return schoolName;
	}
	
	/** 
	* sets the education object school name
	* @param schoolName String
	*/
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	/** 
	* gets the education object from date value
	* @return from date value
	*/
	public Date getFromDate() {
		return fromDate;
	}
	
	/** 
	* sets the education object from date value
	* @param fromDate sql.Date
	*/
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	
	/** 
	* gets the education object to date value
	* @return to date value
	*/
	public Date getToDate() {
		return toDate;
	}
	
	/** 
	* sets the education object to date value
	* @param toDate sql.Date
	*/
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	/** 
	* gets the education object degree type
	* @return degree type
	*/
	public String getDegreeType() {
		return degreeType;
	}
	
	/** 
	* sets the education object degree type
	* @param degreeType String
	*/
	public void setDegreeType(String degreeType) {
		this.degreeType = degreeType;
	}
	
	/** 
	* gets the education object field of study
	* @return field of study value
	*/
	public String getDegreeName() {
		return degreeName;
	}
	
	/** 
	* sets the education object field of study
	* @param degreeName String
	*/
	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}
	
	/** 
	* gets the education object description
	* @return description
	*/
	public String getDescription() {
		return description;
	}
	
	/** 
	* sets the education object description
	* @param description String
	*/
	public void setDescription(String description) {
		this.description = description;
	}
	
	/** 
	* gets the education object delete button object
	* @return delete button
	*/
	public Button getDeleteButton() {
		return deleteButton;
	}

	/** 
	* sets the education object delete button object
	* @param deleteButton Button
	*/
	public void setDeleteButton(Button deleteButton) {
		this.deleteButton = deleteButton;
	}
	
	/** 
	* gets the education object edit button object
	* @return edit button
	*/
	public Button getEditButton() {
		return editButton;
	}

	/** 
	* sets the education object edit button object
	* @param editButton Button
	*/
	public void setEditButton(Button editButton) {
		this.editButton = editButton;
	}

	/** 
	* toString method
	* @return String
	*/
	@Override
	public String toString() {
		return "Education [id=" + id + ", schoolName=" + schoolName + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", degreeType=" + degreeType + ", degreeName=" + degreeName + ", description=" + description + "]";
	}

}
