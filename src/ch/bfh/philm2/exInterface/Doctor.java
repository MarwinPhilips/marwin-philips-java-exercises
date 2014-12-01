package ch.bfh.philm2.exInterface;

public class Doctor extends Person {	
	private String title;
	private String phoneNumber;
	public Doctor(String firstName, String lastName, String title, String phoneNumber ){
		super(firstName,lastName);
		this.setTitle(title);
		this.setPhoneNumber(phoneNumber);
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}