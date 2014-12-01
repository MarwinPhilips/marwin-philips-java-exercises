package ch.bfh.philm2.exInterface;

import ch.bfh.philm2.exercises.Adress;

public class Patient extends Person {
	private int PersID;
	private Adress adress;
	public Patient(String firstName, String lastName, int PersID, Adress adress ){
		super(firstName,lastName);
		this.setPersID(PersID);
		this.setAdress(adress);
	}
	public int getPersID() {
		return PersID;
	}

	public void setPersID(int persID) {
		PersID = persID;
	}

	public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}
}
