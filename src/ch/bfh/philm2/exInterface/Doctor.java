package ch.bfh.philm2.exInterface;

public class Doctor implements Person{
	private String firstName;
	private String lastName;
	private String title;
	private String phoneNumber;
	public Doctor(String firstName, String lastName, String title, String phoneNumber ){
		this.firstName=firstName;
		this.lastName=lastName;
		this.setTitle(title);
		this.setPhoneNumber(phoneNumber);
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
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