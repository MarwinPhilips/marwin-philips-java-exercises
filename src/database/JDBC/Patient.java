package database.JDBC;

import java.sql.Date;

import javax.persistence.Table;

@Table()
public class Patient {
	private int patientNb;
	private int insuranceNb;
	private String name;
	private String firstname;
	private String gender;
	private String street;
	private String zip;
	private String city;
	private String phone;
	private Date birthDate;

	public Patient(int patientNb, String name, String firstname, String gender,
			Date birthDate, String street, String ZIP, String city,
			String phone, int insuranceNb) {
		this.patientNb=patientNb;
		this.name=name;
		this.firstname=firstname;
		this.gender=gender;
		this.birthDate=birthDate;
		this.street=street;
		this.zip=ZIP;
		this.city=city;
		this.phone=phone;
		this.insuranceNb=insuranceNb;
	}

	public int getPatientNb() {
		return patientNb;
	}

	public void setPatientNb(int patientNb) {
		this.patientNb = patientNb;
	}

	public int getInsuranceNb() {
		return insuranceNb;
	}

	public void setInsuranceNb(int insuranceNb) {
		this.insuranceNb = insuranceNb;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

}
