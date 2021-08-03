package application.model;

/*
 * Summary Model class
 * 
 * MyUTSA ID: gos049
 * Assignment: Resume Builder Project
 * Class: CS-3443-01T-Summer-2021-Application Programming
 * 
 * @author: Hamza Hamdan
 * 
 */

public class Summary {
	
	/** 
	* text from the summary record
	*/
	private String summary;
	
	/** 
	* Summary class no-args constructor
	* 
	*/
	public Summary() {
		super();
	}
	
	/** 
	* Summary class constructor
	* @param summary String
	*/
	public Summary(String summary) {
		super();
		this.summary = summary;
	}
	
	/** 
	* gets the Summary object summary value
	* @return summary
	*/
	public String getSummary() {
		return summary;
	}
	
	/** 
	* sets the summary object summary value
	* @param summary String
	*/
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/** 
	* toString method
	* @return String
	*/
	@Override
	public String toString() {
		return "Summary [summary=" + summary + "]";
	}

}
