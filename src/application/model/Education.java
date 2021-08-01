package application.model;

import java.sql.Date;

import javafx.scene.control.Button;

public class Education {
	
	private long id;
	private String schoolName;
	private Date fromDate;
	private Date toDate;
	private String degreeType;
	private String degreeName;
	private String description;
	private Button deleteButton;
	private Button editButton;
	
	public Education() {
		super();
	}
	
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
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
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
	public String getDegreeType() {
		return degreeType;
	}
	public void setDegreeType(String degreeType) {
		this.degreeType = degreeType;
	}
	public String getDegreeName() {
		return degreeName;
	}
	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
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
		return "Education [id=" + id + ", schoolName=" + schoolName + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", degreeType=" + degreeType + ", degreeName=" + degreeName + ", description=" + description + "]";
	}

}
