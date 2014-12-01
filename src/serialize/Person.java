package serialize;

import java.io.Serializable;


public class Person implements Serializable {
	private final int year;
	private final int month;
	private final int day;
	private String password;
	private final String name;
	
	public Person(int year, int month, int day, String name, String password){
		this.year=year;
		this.month = month;
		this.day=day;
		this.name=name;
		this.password=password;
	}
	public int getYear() {
		return year;
	}
	public int getMonth() {
		return month;
	}
	public int getDay() {
		return day;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	} 
	public void SetPassword(String password){
		this.password=password;
	}
	@Override
	public String toString() {
		return "Person [year=" + year + ", month=" + month + ", day=" + day
				+ ", name=" + name + ", password: "+password +"]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + month;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + year;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (day != other.day)
			return false;
		if (month != other.month)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
}
