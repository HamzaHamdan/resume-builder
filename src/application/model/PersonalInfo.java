package application.model;

public class PersonalInfo {
	
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String phoneNumber;
	private String physicalAddress;
	
	public PersonalInfo(String firstName, String lastName, String emailAddress, String phoneNumber, String physicalAddress) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.physicalAddress = physicalAddress;
	}

	public PersonalInfo() {
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhysicalAddress() {
		return physicalAddress;
	}

	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}

	@Override
	public String toString() {
		return "PersonlaInfo [firstName=" + firstName + ", lastName=" + lastName + ", emailAddress=" + emailAddress
				+ ", phoneNumebr=" + phoneNumber + ", physicalAddress=" + physicalAddress + "]";
	}

}
