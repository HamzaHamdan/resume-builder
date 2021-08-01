package application.model;

import javafx.scene.control.Button;

public class Skill {
	
	private long id;
	private String skillDesc;
	private Button deleteButton;
	private Button editButton;
	
	public Skill() {
		super();
	}
	
	public Skill(long id, String skillDesc, Button deleteButton, Button editButton) {
		super();
		this.id = id;
		this.skillDesc = skillDesc;
		this.deleteButton = new Button();
		this.editButton = new Button();
	}

	public Button getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(Button deleteButton) {
		this.deleteButton = deleteButton;
	}

	public Button getEditButton() {
		return editButton;
	}

	public void setEditButton(Button editButton) {
		this.editButton = editButton;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getSkillDesc() {
		return skillDesc;
	}
	
	public void setSkillDesc(String skillDesc) {
		this.skillDesc = skillDesc;
	}

	@Override
	public String toString() {
		return "Skill [id=" + id + ", skillDesc=" + skillDesc + ", deleteButton=" + deleteButton + ", editButton=" + editButton
				+ "]";
	}

}
