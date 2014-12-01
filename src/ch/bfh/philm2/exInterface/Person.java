package ch.bfh.philm2.exInterface;

public class Person implements iPerson {
	String firstName;
	protected String lastName;
	public Person(String firstName, String lastName){
		this.firstName=firstName;
		this.lastName=lastName;
	}
	public String getFirstName() {
		return firstName;
	}	
	public String getLastName() {
		return lastName;
	}
	@Override
	public String toString(){
		return firstName + " " + lastName;
	}
	@Override
	public boolean equals(Object other)
	{
		if(other==this){
			return true;
		}else{
			if(!(other instanceof Person))
			{	
				return false;
			}else{
				Person otherPerson = (Person)other;
				return firstName==otherPerson.firstName&&lastName==otherPerson.lastName;
			}
		}
	}
}
