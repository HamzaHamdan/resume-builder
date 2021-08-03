package application.model;

/*
 * Skill Model class
 * 
 * MyUTSA ID: gos049
 * Assignment: Resume Builder Project
 * Class: CS-3443-01T-Summer-2021-Application Programming
 * 
 * @author: Hamza Hamdan
 * 
 */

import javafx.scene.control.Button;

public class Skill {
	
	/** 
	* id of the skill record
	*/
	private long id;
	
	/** 
	* description from the skill record
	*/
	private String skillDesc;
	
	/** 
	* skill record delete button
	*/
	private Button deleteButton;
	
	/** 
	* skill record edit button
	*/
	private Button editButton;
	
	/** 
	* Skill class no-args constructor
	* 
	*/
	public Skill() {
		super();
	}
	
	/** 
	* Experience class constructor
	* @param id long
	* @param skillDesc String
	* @param deleteButton Button
	* @param editButton Button
	*/
	public Skill(long id, String skillDesc, Button deleteButton, Button editButton) {
		super();
		this.id = id;
		this.skillDesc = skillDesc;
		this.deleteButton = new Button();
		this.editButton = new Button();
	}

	/** 
	* gets the skill object delete button object
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
	* gets the skill object edit button object
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
	* gets the skill object description value
	* @return description
	*/
	public String getSkillDesc() {
		return skillDesc;
	}
	
	/** 
	* sets the skill object description value
	* @param skillDesc String
	*/
	public void setSkillDesc(String skillDesc) {
		this.skillDesc = skillDesc;
	}

	/** 
	* toString method
	* @return String
	*/
	@Override
	public String toString() {
		return "Skill [id=" + id + ", skillDesc=" + skillDesc + ", deleteButton=" + deleteButton + ", editButton=" + editButton
				+ "]";
	}

}
