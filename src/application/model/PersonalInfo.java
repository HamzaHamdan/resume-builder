package application.model;

/*
 * PersonalInfo Model class
 * 
 * MyUTSA ID: gos049
 * Assignment: Resume Builder Project
 * Class: CS-3443-01T-Summer-2021-Application Programming
 * 
 * @author: Hamza Hamdan
 * 
 */

public class PersonalInfo {
	
	/** 
	* first name of the user
	*/
	private String firstName;
	
	/** 
	* last name of the user
	*/
	private String lastName;
	
	/** 
	* email address of the user
	*/
	private String emailAddress;
	
	/** 
	* phone number of the user
	*/
	private String phoneNumber;
	
	/** 
	* physical address of the user
	*/
	private String physicalAddress;
	
	/** 
	* PersonalInfo class constructor
	* @param firstName String
	* @param lastName String
	* @param emailAddress String
	* @param phoneNumber String
	* @param physicalAddress String
	*/
	public PersonalInfo(String firstName, String lastName, String emailAddress, String phoneNumber, String physicalAddress) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.physicalAddress = physicalAddress;
	}

	/** 
	* PersonalInfo class no-args constructor
	* 
	*/
	public PersonalInfo() {
		super();
	}

	/** 
	* gets the first name from PersonlaInfo object
	* @return first name of the user
	*/
	public String getFirstName() {
		return firstName;
	}

	/** 
	* sets the first name value in the Personal object
	* @param firstName String
	*/
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/** 
	* gets the last name from PersonlaInfo object
	* @return last name of the user
	*/
	public String getLastName() {
		return lastName;
	}

	/** 
	* sets the last name value in the Personal object
	* @param lastName String
	*/
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/** 
	* gets the email address from PersonlaInfo object
	* @return email address of the user
	*/
	public String getEmailAddress() {
		return emailAddress;
	}

	/** 
	* sets the email address value in the Personal object
	* @param emailAddress String
	*/
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/** 
	* gets the phone number from PersonlaInfo object
	* @return phone number of the user
	*/
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/** 
	* sets the phone number value in the Personal object
	* @param phoneNumber String
	*/
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/** 
	* gets the physical address from PersonlaInfo object
	* @return physcial address of the user
	*/
	public String getPhysicalAddress() {
		return physicalAddress;
	}

	/** 
	* sets the physical address value in the Personal object
	* @param physicalAddress String
	*/
	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}

	/** 
	* toString method
	* @return String
	*/
	@Override
	public String toString() {
		return "PersonlaInfo [firstName=" + firstName + ", lastName=" + lastName + ", emailAddress=" + emailAddress
				+ ", phoneNumebr=" + phoneNumber + ", physicalAddress=" + physicalAddress + "]";
	}

}
