package application.model;

/*
 * Experience Model class
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

public class Experience {

	/** 
	* id of the education record
	*/
	private long id;
	
	/** 
	* name of the company in the experience record
	*/
	private String comapnyName;
	
	/** 
	* experience record from date value
	*/
	private Date fromDate;
	
	/** 
	* experience record from date value
	*/
	private Date toDate;
	
	/** 
	* position in the experience record
	*/
	private String position;
	
	/** 
	* description in the experience record
	*/
	private String description;
	
	/** 
	* experience record delete button
	*/
	private Button deleteButton;
	
	/** 
	* experience record edit button
	*/
	private Button editButton;
	
	/** 
	* Experience class no-args constructor
	* 
	*/
	public Experience() {
		super();
	}
	
	/** 
	* Experience class constructor
	* @param id long
	* @param comapnyName String
	* @param fromDate sql.Date
	* @param toDate sql.Date
	* @param position String
	* @param description String
	* @param deleteButton Button
	* @param editButton Button
	*/
	public Experience(long id, String comapnyName, Date fromDate, Date toDate, String position,
			String description, Button deleteButton, Button editButton) {
		super();
		this.id = id;
		this.comapnyName = comapnyName;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.position = position;
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
	* gets the experience object company name
	* @return name of the company
	*/
	public String getComapnyName() {
		return comapnyName;
	}

	/** 
	* sets the experience object company name
	* @param comapnyName String
	*/
	public void setComapnyName(String comapnyName) {
		this.comapnyName = comapnyName;
	}

	/** 
	* gets the experience object from date value
	* @return from date value
	*/
	public Date getFromDate() {
		return fromDate;
	}

	/** 
	* sets the experience object from date value
	* @param fromDate sql.Date
	*/
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/** 
	* gets the experience object to date value
	* @return to date value
	*/
	public Date getToDate() {
		return toDate;
	}

	/** 
	* sets the experience object to date value
	* @param toDate sql.Date
	*/
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	/** 
	* gets the experience object position value
	* @return position
	*/
	public String getPosition() {
		return position;
	}

	/** 
	* sets the experience object position value
	* @param position String
	*/
	public void setPosition(String position) {
		this.position = position;
	}

	/** 
	* gets the experience object description
	* @return description
	*/
	public String getDescription() {
		return description;
	}

	/** 
	* sets the experience object description
	* @param description String
	*/
	public void setDescription(String description) {
		this.description = description;
	}

	/** 
	* gets the experience object delete button object
	* @return delete button
	*/
	public Button getDeleteButton() {
		return deleteButton;
	}

	/** 
	* sets the experience object delete button object
	* @param deleteButton Button
	*/
	public void setDeleteButton(Button deleteButton) {
		this.deleteButton = deleteButton;
	}

	/** 
	* gets the experience object edit button object
	* @return edit button
	*/
	public Button getEditButton() {
		return editButton;
	}

	/** 
	* sets the experience object edit button object
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
		return "Experience [id=" + id + ", comapnyName=" + comapnyName + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", position=" + position + ", description=" + description + ", deleteButton=" + deleteButton
				+ ", editButton=" + editButton + "]";
	}

}
