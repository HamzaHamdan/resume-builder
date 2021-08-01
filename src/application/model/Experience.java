package application.model;

import java.sql.Date;

import javafx.scene.control.Button;

public class Experience {
	
	private long id;
	private String comapnyName;
	private Date fromDate;
	private Date toDate;
	private String position;
	private String description;
	private Button deleteButton;
	private Button editButton;
	
	public Experience() {
		super();
	}
	
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComapnyName() {
		return comapnyName;
	}

	public void setComapnyName(String comapnyName) {
		this.comapnyName = comapnyName;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Override
	public String toString() {
		return "Experience [id=" + id + ", comapnyName=" + comapnyName + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", position=" + position + ", description=" + description + ", deleteButton=" + deleteButton
				+ ", editButton=" + editButton + "]";
	}

}
