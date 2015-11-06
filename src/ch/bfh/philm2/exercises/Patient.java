package ch.bfh.philm2.exercises;

import java.time.LocalDate;
import java.util.List;

import ch.bfh.philm2.util.DeltaTime;

public class Patient{
	private int patientId;
	private String name;
	private String vorname;
	private char geschlecht;
	private int gewicht;
	private int Grösse;
	private LocalDate geburtsdatum;
	private List<Fall> fälle;
	
	public Patient(int patientId, String name, String vorname, char geschlecht, int gewicht, int Grösse, LocalDate geburtsdatum){
		this.patientId=patientId;
		this.name=name;
		this.vorname=vorname;
		this.geschlecht=geschlecht;
		this.gewicht=gewicht;
		this.Grösse=Grösse;
		this.geburtsdatum=geburtsdatum;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public LocalDate getGeburtsdatum() {
		return geburtsdatum;
	}
	public void setGeburtsdatum(LocalDate geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}
	public List<Fall> getfälle() {
		return fälle;
	}
	public void setfälle(List<Fall> fälle) {
		this.fälle = fälle;
	}
	public void AddFall(Fall fall) {
		this.fälle.add(fall);		
	}
	public void removeFall(Fall fallToRemove) {
		fälle.remove(fallToRemove);		
	}
	public char getGeschlecht() {
		return geschlecht;
	}
	public void setGeschlecht(char geschlecht) {
		this.geschlecht = geschlecht;
	}
	public int getGewicht() {
		return gewicht;
	}
	public void setGewicht(int gewicht) {
		this.gewicht = gewicht;
	}
	public int getGrösse() {
		return Grösse;
	}
	public void setGrösse(int Grösse) {
		this.Grösse = Grösse;
	}
	public String getAge(){
		DeltaTime d = new DeltaTime(LocalDate.now(), geburtsdatum);	
		return d.getDeltaTimeInYearsMonthsDays();
	}
}